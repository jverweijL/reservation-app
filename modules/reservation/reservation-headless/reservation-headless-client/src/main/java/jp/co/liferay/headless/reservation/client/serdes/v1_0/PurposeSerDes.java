package jp.co.liferay.headless.reservation.client.serdes.v1_0;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import jp.co.liferay.headless.reservation.client.dto.v1_0.Purpose;
import jp.co.liferay.headless.reservation.client.json.BaseJSONParser;

/**
 * @author Maurice Sepe
 * @generated
 */
@Generated("")
public class PurposeSerDes {

	public static Purpose toDTO(String json) {
		PurposeJSONParser purposeJSONParser = new PurposeJSONParser();

		return purposeJSONParser.parseToDTO(json);
	}

	public static Purpose[] toDTOs(String json) {
		PurposeJSONParser purposeJSONParser = new PurposeJSONParser();

		return purposeJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Purpose purpose) {
		if (purpose == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (purpose.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(purpose.getName()));

			sb.append("\"");
		}

		if (purpose.getPurposeId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"purposeId\": ");

			sb.append(purpose.getPurposeId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		PurposeJSONParser purposeJSONParser = new PurposeJSONParser();

		return purposeJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Purpose purpose) {
		if (purpose == null) {
			return null;
		}

		Map<String, String> map = new HashMap<>();

		if (purpose.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(purpose.getName()));
		}

		if (purpose.getPurposeId() == null) {
			map.put("purposeId", null);
		}
		else {
			map.put("purposeId", String.valueOf(purpose.getPurposeId()));
		}

		return map;
	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append("\"");
			sb.append(entry.getValue());
			sb.append("\"");

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static class PurposeJSONParser extends BaseJSONParser<Purpose> {

		@Override
		protected Purpose createDTO() {
			return new Purpose();
		}

		@Override
		protected Purpose[] createDTOArray(int size) {
			return new Purpose[size];
		}

		@Override
		protected void setField(
			Purpose purpose, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					purpose.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "purposeId")) {
				if (jsonParserFieldValue != null) {
					purpose.setPurposeId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

}