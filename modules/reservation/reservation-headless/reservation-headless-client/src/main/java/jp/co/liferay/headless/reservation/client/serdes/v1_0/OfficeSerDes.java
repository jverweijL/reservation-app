package jp.co.liferay.headless.reservation.client.serdes.v1_0;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

import jp.co.liferay.headless.reservation.client.dto.v1_0.Office;
import jp.co.liferay.headless.reservation.client.json.BaseJSONParser;

/**
 * @author Maurice Sepe
 * @generated
 */
@Generated("")
public class OfficeSerDes {

	public static Office toDTO(String json) {
		OfficeJSONParser officeJSONParser = new OfficeJSONParser();

		return officeJSONParser.parseToDTO(json);
	}

	public static Office[] toDTOs(String json) {
		OfficeJSONParser officeJSONParser = new OfficeJSONParser();

		return officeJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Office office) {
		if (office == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (office.getLocation() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"location\": ");

			sb.append("\"");

			sb.append(_escape(office.getLocation()));

			sb.append("\"");
		}

		if (office.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(office.getName()));

			sb.append("\"");
		}

		if (office.getOfficeId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"officeId\": ");

			sb.append(office.getOfficeId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		OfficeJSONParser officeJSONParser = new OfficeJSONParser();

		return officeJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Office office) {
		if (office == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (office.getLocation() == null) {
			map.put("location", null);
		}
		else {
			map.put("location", String.valueOf(office.getLocation()));
		}

		if (office.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(office.getName()));
		}

		if (office.getOfficeId() == null) {
			map.put("officeId", null);
		}
		else {
			map.put("officeId", String.valueOf(office.getOfficeId()));
		}

		return map;
	}

	public static class OfficeJSONParser extends BaseJSONParser<Office> {

		@Override
		protected Office createDTO() {
			return new Office();
		}

		@Override
		protected Office[] createDTOArray(int size) {
			return new Office[size];
		}

		@Override
		protected void setField(
			Office office, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "location")) {
				if (jsonParserFieldValue != null) {
					office.setLocation((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					office.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "officeId")) {
				if (jsonParserFieldValue != null) {
					office.setOfficeId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (jsonParserFieldName.equals("status")) {
				throw new IllegalArgumentException();
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
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

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}