package com.jci.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jci.transfer.BatteryOperationRequest;
import com.jci.util.ApplicationConstants;

@Aspect
@Component
public class RequestValidationAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* com.jci.controller..*(..)) && args(@RequestBody body,..)")
	public void onExecute(JoinPoint joinPoint, Object body) throws MissingServletRequestParameterException {

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method methods = methodSignature.getMethod();
		RequestMethod[] requestMethods = methods.getAnnotation(RequestMapping.class).method();
		String method = requestMethods[0].toString();
		List<String> messageBuilder = new ArrayList<>();
		String messageId = null;
		String channel = null;

		logger.debug("Validating Request {} ... ", methodSignature.getName());

		if (((method != null && method.equals("POST")) || (method != null && method.equals("DELETE")))) {

			BatteryOperationRequest req = (BatteryOperationRequest) body;
			messageId = req.getMessageId();
			channel = req.getChannel();

			if (messageId == null || messageId.trim().length() == 0) {
				messageBuilder.add("messageId");
			}
			if (channel == null || channel.trim().length() == 0) {
				messageBuilder.add("channel");
			}

			if (method.equals("POST")) {
				Timestamp requestTime = req.getRequestTime();
				String serialNo = req.getSerialNo();
				Integer operationTypeId = req.getOperationTypeId();
				Timestamp operationTime = req.getOperationTime();
				String operatorSourceSystem = req.getOperatorSourceSystem();
				String operator = req.getOperator();
				String operationFrom = req.getOperationFrom();
				String operationDest = req.getOperationDest();

				messageBuilder = checkFieldPresence(requestTime, messageBuilder, "requestTime");
				messageBuilder = checkFieldPresence(serialNo, messageBuilder, "serialNo");
				messageBuilder = checkFieldPresence(operationTypeId, messageBuilder, "operationTypeId");
				messageBuilder = checkFieldPresence(operationTime, messageBuilder, "operationTime");
				messageBuilder = checkFieldPresence(operatorSourceSystem, messageBuilder, "operatorSourceSystem");
				messageBuilder = checkFieldPresence(operator, messageBuilder, "operator");
				messageBuilder = checkFieldPresence(operationFrom, messageBuilder, "operationFrom");
				messageBuilder = checkFieldPresence(operationDest, messageBuilder, "operationDest");

			} else if (method.equals("DELETE")) {
				Integer operationId = req.getOperationId();
				messageBuilder = checkFieldPresence(operationId, messageBuilder, "operationId");
			}

		} else {
			messageBuilder = validateGETRequest(joinPoint, methodSignature, messageBuilder);
		}

		if (messageBuilder != null && !messageBuilder.isEmpty()) {
			throw new MissingServletRequestParameterException(messageBuilder.toString(), "String");
		}

	}

	private List<String> validateGETRequest(JoinPoint joinPoint, MethodSignature methodSignature,
			List<String> messageBuilder) {
		Object parameterValue;
		String requestParamName = null;

		Object[] signatureArgs = joinPoint.getArgs();
		Annotation[][] parameterAnnotations = methodSignature.getMethod().getParameterAnnotations();

		assert signatureArgs.length == parameterAnnotations.length;
		for (int argIndex = 0; argIndex < signatureArgs.length; argIndex++) {
			for (Annotation annotation : parameterAnnotations[argIndex]) {
				RequestParam requestParam = (RequestParam) annotation;

				requestParamName = requestParam.name();
				parameterValue = (Object) signatureArgs[argIndex];

				logger.debug("requestParamName : {}, Value : {}", requestParamName, requestParam.value());
				if ((ApplicationConstants.CHANNEL.equalsIgnoreCase(requestParamName))
						|| (ApplicationConstants.MESSAGE_ID.equalsIgnoreCase(requestParamName))
						|| (ApplicationConstants.SERIAL_NO.equalsIgnoreCase(requestParamName))) {

					

					if (parameterValue instanceof String[]) {
						String[] paramVal = (String[]) parameterValue;
						if (paramVal == null || paramVal.length == 0) {
							messageBuilder.add(requestParamName);
						}
					} else if (parameterValue instanceof ArrayList<?>) {
						ArrayList<String> paramVal = (ArrayList<String>) parameterValue;
						if (paramVal == null || paramVal.isEmpty()) {
							messageBuilder.add(requestParamName);
						}
					} else {
						if (parameterValue == null || parameterValue.toString().trim().isEmpty()) {
							messageBuilder.add(requestParamName);
						}
					}

				}
				else
				{
					if(parameterValue != null) {
						if (parameterValue instanceof String[]) {
							String[] paramVal = (String[]) parameterValue;
							if (paramVal.length == 0) {
								messageBuilder.add(requestParamName);
							}
						} else if (parameterValue instanceof ArrayList<?>) {
							ArrayList<String> paramVal = (ArrayList<String>) parameterValue;
							if (paramVal.isEmpty()) {
								messageBuilder.add(requestParamName);
							}
						} else {
							if (parameterValue.toString().trim().isEmpty()) {
								messageBuilder.add(requestParamName);
							}
						}
					}
				}
			}
		}
		return messageBuilder;
	}

	private List<String> checkFieldPresence(Object fieldName, List<String> messageBuilder, String actualFieldName) {
		if (fieldName == null) {
			messageBuilder.add(actualFieldName);
		}

		return messageBuilder;
	}
}
