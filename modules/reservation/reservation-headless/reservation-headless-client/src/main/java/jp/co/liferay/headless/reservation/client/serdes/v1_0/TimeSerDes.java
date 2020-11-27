package jp.co.liferay.headless.reservation.client.serdes.v1_0;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

import jp.co.liferay.headless.reservation.client.dto.v1_0.Time;
import jp.co.liferay.headless.reservation.client.json.BaseJSONParser;

/**
 * @author Maurice Sepe
 * @generated
 */
@Generated("")
public class TimeSerDes {

	public static Time toDTO(String json) {
		TimeJSONParser timeJSONParser = new TimeJSONParser();

		return timeJSONParser.parseToDTO(json);
	}

	public static Time[] toDTOs(String json) {
		TimeJSONParser timeJSONParser = new TimeJSONParser();

		return timeJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Time time) {
		if (time == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (time.getAmpm() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ampm\": ");

			sb.append("\"");

			sb.append(_escape(time.getAmpm()));

			sb.append("\"");
		}

		if (time.getHours() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"hours\": ");

			sb.append("\"");

			sb.append(_escape(time.getHours()));

			sb.append("\"");
		}

		if (time.getMinutes() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"minutes\": ");

			sb.append("\"");

			sb.append(_escape(time.getMinutes()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		TimeJSONParser timeJSONParser = new TimeJSONParser();

		return timeJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Time time) {
		if (time == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (time.getAmpm() == null) {
			map.put("ampm", null);
		}
		else {
			map.put("ampm", String.valueOf(time.getAmpm()));
		}

		if (time.getHours() == null) {
			map.put("hours", null);
		}
		else {
			map.put("hours", String.valueOf(time.getHours()));
		}

		if (time.getMinutes() == null) {
			map.put("minutes", null);
		}
		else {
			map.put("minutes", String.valueOf(time.getMinutes()));
		}

		return map;
	}

	public static class TimeJSONParser extends BaseJSONParser<Time> {

		@Override
		protected Time createDTO() {
			return new Time();
		}

		@Override
		protected Time[] createDTOArray(int size) {
			return new Time[size];
		}

		@Override
		protected void setField(
			Time time, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "ampm")) {
				if (jsonParserFieldValue != null) {
					time.setAmpm((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "hours")) {
				if (jsonParserFieldValue != null) {
					time.setHours((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "minutes")) {
				if (jsonParserFieldValue != null) {
					time.setMinutes((String)jsonParserFieldValue);
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