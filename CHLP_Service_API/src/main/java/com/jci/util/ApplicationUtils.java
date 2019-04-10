package com.jci.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jci.common.core.InjectableLogger;
import com.jci.common.exception.InvalidDateFormatException;
import com.jci.common.exception.StringTooLongException;

@Configuration
public class ApplicationUtils {

	@InjectableLogger
	private static Logger logger;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public void validateLengthTypeList(List<String> inputParam) throws StringTooLongException {
		logger.debug("RequestParam  : {}", inputParam);
		List<String> invalidLengthList = new ArrayList<>();
		List<String> nonNumeric = new ArrayList<>();

		for (String requestParam : inputParam) {
			if (requestParam.length() > ApplicationConstants.DBSERIALNOLENGTH) {

				invalidLengthList.add(requestParam);
			}
			if (!requestParam.matches(ApplicationConstants.REQUESTPARAMPATTERN)) {

				nonNumeric.add(requestParam);
			}
		}
		if (invalidLengthList.isEmpty()) {
			if (!nonNumeric.isEmpty()) {
				throw new NumberFormatException(nonNumeric.toString());
			}
		} else {
			throw new StringTooLongException(invalidLengthList.toString());
		}

	}

	public void validateLengthTypeString(String inputParam) throws StringTooLongException {
		logger.debug("RequestParam  : {}", inputParam);
		if (inputParam.length() > ApplicationConstants.DBSERIALNOLENGTH) {
			throw new StringTooLongException(inputParam);
		}
		if (!inputParam.matches(ApplicationConstants.REQUESTPARAMPATTERN)) {
			throw new NumberFormatException(inputParam);
		}
	}

	public void validateLengthTypeAndAlphnumericList(List<String> inputParam) throws StringTooLongException {
		logger.debug("RequestParam  : {}", inputParam);
		List<String> invalidLengthList = new ArrayList<>();
		List<String> nonAlphaNumeric = new ArrayList<>();

		for (String requestParam : inputParam) {
			if (requestParam.length() > ApplicationConstants.DBSERIALNOLENGTH) {

				invalidLengthList.add(requestParam);
			}
			if (!requestParam.matches(ApplicationConstants.ALPHANUMERIC_PATTERN)) {

				nonAlphaNumeric.add(requestParam);
			}
		}
		if (invalidLengthList.isEmpty()) {
			if (!nonAlphaNumeric.isEmpty()) {
				throw new IllegalArgumentException(nonAlphaNumeric.toString());
			}
		} else {
			throw new StringTooLongException(invalidLengthList.toString());
		}

	}

	public static void validateDateField(List<String> dateFieldList) {
		Date date = null;

		for (String dateField : dateFieldList) {
			if (dateField != null && !dateField.isEmpty()) {
				if (dateField.length() != ApplicationConstants.DATEFIELDLENGTH) {

					throw new InvalidDateFormatException(dateField);
				}
				if (!dateField.matches(ApplicationConstants.REQUESTPARAMPATTERN)) {
					throw new InvalidDateFormatException(dateField);
				} else {

					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					try {
						date = sdf.parse(dateField);
						if (!dateField.equals(sdf.format(date))) {
							throw new InvalidDateFormatException(dateField);
						}
					} catch (ParseException e) {
						throw new InvalidDateFormatException(dateField);
					}

				}
			}

		}

	}

	public static StringBuilder getQueryWhereClause(Map<String, List<String>> fieldValueMap,
			boolean isfirstFieldPresent) {

		StringBuilder queryBuilder = null;
		if (fieldValueMap != null && !fieldValueMap.isEmpty()) {
			queryBuilder = new StringBuilder();
			Set<String> keySet = fieldValueMap.keySet();
			List<String> fieldValue = null;

			for (String fieldName : keySet) {
				fieldValue = fieldValueMap.get(fieldName);

				if (fieldValue != null && !fieldValue.isEmpty()) {

					if (!isfirstFieldPresent) {
						queryBuilder.append(" WHERE ");
					}

					queryBuilder.append(checkIfFirstFieldIsPresent(isfirstFieldPresent));
					queryBuilder.append(fieldName);
					queryBuilder.append(" IN (");
					queryBuilder.append(":");
					queryBuilder.append(fieldName);
					queryBuilder.append(")");
					isfirstFieldPresent = true;
				}
			}
		}

		return queryBuilder;
	}

	public static StringBuilder getRangeClauseFilter(boolean isWhereClausePresent,
			Map<String, String> rangeFromValueMap, Map<String, String> rangeToValueMap) {

		StringBuilder rangeClauseBuilder = new StringBuilder();
		boolean isFirstFieldPresent = false;

		if (rangeFromValueMap != null && !rangeFromValueMap.isEmpty()) {
			rangeClauseBuilder = new StringBuilder();
			Set<String> keySet = rangeFromValueMap.keySet();
			String fieldValue = null;

			for (String fieldName : keySet) {

				fieldValue = rangeFromValueMap.get(fieldName);
				if (fieldValue != null && !fieldValue.isEmpty()) {

					if (!isWhereClausePresent) {
						rangeClauseBuilder.append(" WHERE ");
						isWhereClausePresent = true;
					} else {
						isFirstFieldPresent = true;
					}

					if (isFirstFieldPresent) {
						rangeClauseBuilder.append(ApplicationConstants.QUERY_BUILDER_OR);
					}

					isFirstFieldPresent = true;
					rangeClauseBuilder.append(fieldName);
					rangeClauseBuilder.append(" >= ");
					rangeClauseBuilder.append(":");
					rangeClauseBuilder.append(fieldName);
					rangeClauseBuilder.append("1");

					if (rangeToValueMap != null && !rangeToValueMap.isEmpty()) {

						fieldValue = rangeToValueMap.get(fieldName);

						if (fieldValue != null && !fieldValue.isEmpty()) {

							rangeClauseBuilder.append(ApplicationConstants.QUERY_BUILDER_AND);
							rangeClauseBuilder.append(fieldName);
							rangeClauseBuilder.append(" <= ");
							rangeClauseBuilder.append(":");
							rangeClauseBuilder.append(fieldName);
							rangeClauseBuilder.append("2");
						}

					}
				}
			}
		}

		return rangeClauseBuilder;

	}

	public static Query setParameterValues(Query query, Map<String, List<String>> fieldValueMap,
			Map<String, String> rangeFromValueMap, Map<String, String> rangeToValueMap) {

		if (query != null) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date formattedDate;
			String parameterName = null;

			try {
				if (fieldValueMap != null && !fieldValueMap.isEmpty()) {
					Set<String> keySet = fieldValueMap.keySet();
					List<String> fieldValue = new ArrayList<>();

					for (String fieldName : keySet) {
						fieldValue = fieldValueMap.get(fieldName);

						if (fieldValue != null && !fieldValue.isEmpty()) {
							query.setParameter(fieldName, fieldValue);

						}
					}
				}

				if (rangeFromValueMap != null && !rangeFromValueMap.isEmpty()) {
					Set<String> keySet = rangeFromValueMap.keySet();
					String fieldValue = null;

					for (String fieldName : keySet) {

						fieldValue = rangeFromValueMap.get(fieldName);
						if (fieldValue != null && !fieldValue.isEmpty()) {
							formattedDate = sdf.parse(fieldValue);
							parameterName = fieldName + "1";
							query.setParameter(parameterName, formattedDate);

							fieldValue = rangeToValueMap.get(fieldName);
							if (fieldValue != null && !fieldValue.isEmpty()) {
								formattedDate = sdf.parse(fieldValue);
								parameterName = fieldName + "2";
								query.setParameter(parameterName, formattedDate);

							}

						}

					}
				}
			}

			catch (ParseException e) {
				logger.error("Exception occurred while parsing Date, Exception Details : {}", e.getMessage());
			}

		}
		return query;

	}

	private static StringBuilder checkIfFirstFieldIsPresent(boolean isfirstFieldPresent) {

		StringBuilder queryBuilder = new StringBuilder();
		if (isfirstFieldPresent) {

			queryBuilder.append(ApplicationConstants.QUERY_BUILDER_OR);
		}

		return queryBuilder;
	}
}
