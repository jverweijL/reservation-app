/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package jp.co.liferay.reservation.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import jp.co.liferay.reservation.model.PersistedRoom;
import jp.co.liferay.reservation.model.PersistedRoomModel;
import jp.co.liferay.reservation.model.PersistedRoomSoap;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the PersistedRoom service. Represents a row in the &quot;Reservation_PersistedRoom&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>PersistedRoomModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PersistedRoomImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PersistedRoomImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class PersistedRoomModelImpl
	extends BaseModelImpl<PersistedRoom> implements PersistedRoomModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a persisted room model instance should use the <code>PersistedRoom</code> interface instead.
	 */
	public static final String TABLE_NAME = "Reservation_PersistedRoom";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"externalReferenceCode", Types.VARCHAR},
		{"roomId", Types.BIGINT}, {"calendarId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"photoPath", Types.VARCHAR},
		{"availableMonday", Types.BOOLEAN}, {"availableTuesday", Types.BOOLEAN},
		{"availableWednesday", Types.BOOLEAN},
		{"availableThursday", Types.BOOLEAN},
		{"availableFriday", Types.BOOLEAN},
		{"availableSaturday", Types.BOOLEAN},
		{"availableSunday", Types.BOOLEAN},
		{"availableStartTime", Types.INTEGER},
		{"availableEndTime", Types.INTEGER}, {"officeId", Types.BIGINT},
		{"capacitySquareMeters", Types.DOUBLE},
		{"capacityPeople", Types.INTEGER}, {"phoneExtension", Types.INTEGER},
		{"wifiSSID", Types.VARCHAR}, {"wifiSecurityType", Types.INTEGER},
		{"wifiPassword", Types.VARCHAR}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("roomId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("calendarId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("photoPath", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("availableMonday", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("availableTuesday", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("availableWednesday", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("availableThursday", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("availableFriday", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("availableSaturday", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("availableSunday", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("availableStartTime", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("availableEndTime", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("officeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("capacitySquareMeters", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("capacityPeople", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("phoneExtension", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("wifiSSID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("wifiSecurityType", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("wifiPassword", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Reservation_PersistedRoom (uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,roomId LONG not null primary key,calendarId LONG,name VARCHAR(75) null,photoPath VARCHAR(255) null,availableMonday BOOLEAN,availableTuesday BOOLEAN,availableWednesday BOOLEAN,availableThursday BOOLEAN,availableFriday BOOLEAN,availableSaturday BOOLEAN,availableSunday BOOLEAN,availableStartTime INTEGER,availableEndTime INTEGER,officeId LONG,capacitySquareMeters DOUBLE,capacityPeople INTEGER,phoneExtension INTEGER,wifiSSID VARCHAR(75) null,wifiSecurityType INTEGER,wifiPassword VARCHAR(75) null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table Reservation_PersistedRoom";

	public static final String ORDER_BY_JPQL =
		" ORDER BY persistedRoom.roomId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Reservation_PersistedRoom.roomId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CALENDARID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 4L;

	public static final long OFFICEID_COLUMN_BITMASK = 8L;

	public static final long UUID_COLUMN_BITMASK = 16L;

	public static final long ROOMID_COLUMN_BITMASK = 32L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static PersistedRoom toModel(PersistedRoomSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		PersistedRoom model = new PersistedRoomImpl();

		model.setUuid(soapModel.getUuid());
		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setRoomId(soapModel.getRoomId());
		model.setCalendarId(soapModel.getCalendarId());
		model.setName(soapModel.getName());
		model.setPhotoPath(soapModel.getPhotoPath());
		model.setAvailableMonday(soapModel.isAvailableMonday());
		model.setAvailableTuesday(soapModel.isAvailableTuesday());
		model.setAvailableWednesday(soapModel.isAvailableWednesday());
		model.setAvailableThursday(soapModel.isAvailableThursday());
		model.setAvailableFriday(soapModel.isAvailableFriday());
		model.setAvailableSaturday(soapModel.isAvailableSaturday());
		model.setAvailableSunday(soapModel.isAvailableSunday());
		model.setAvailableStartTime(soapModel.getAvailableStartTime());
		model.setAvailableEndTime(soapModel.getAvailableEndTime());
		model.setOfficeId(soapModel.getOfficeId());
		model.setCapacitySquareMeters(soapModel.getCapacitySquareMeters());
		model.setCapacityPeople(soapModel.getCapacityPeople());
		model.setPhoneExtension(soapModel.getPhoneExtension());
		model.setWifiSSID(soapModel.getWifiSSID());
		model.setWifiSecurityType(soapModel.getWifiSecurityType());
		model.setWifiPassword(soapModel.getWifiPassword());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<PersistedRoom> toModels(PersistedRoomSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<PersistedRoom> models = new ArrayList<PersistedRoom>(
			soapModels.length);

		for (PersistedRoomSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final String MAPPING_TABLE_RESERVATION_ROOMS_PURPOSES_NAME =
		"Reservation_Rooms_Purposes";

	public static final Object[][]
		MAPPING_TABLE_RESERVATION_ROOMS_PURPOSES_COLUMNS = {
			{"companyId", Types.BIGINT}, {"purposeId", Types.BIGINT},
			{"roomId", Types.BIGINT}
		};

	public static final String
		MAPPING_TABLE_RESERVATION_ROOMS_PURPOSES_SQL_CREATE =
			"create table Reservation_Rooms_Purposes (companyId LONG not null,purposeId LONG not null,roomId LONG not null,primary key (purposeId, roomId))";

	public static final String MAPPING_TABLE_RESERVATION_ROOMS_AMENITIES_NAME =
		"Reservation_Rooms_Amenities";

	public static final Object[][]
		MAPPING_TABLE_RESERVATION_ROOMS_AMENITIES_COLUMNS = {
			{"companyId", Types.BIGINT}, {"amenityId", Types.BIGINT},
			{"roomId", Types.BIGINT}
		};

	public static final String
		MAPPING_TABLE_RESERVATION_ROOMS_AMENITIES_SQL_CREATE =
			"create table Reservation_Rooms_Amenities (companyId LONG not null,amenityId LONG not null,roomId LONG not null,primary key (amenityId, roomId))";

	public PersistedRoomModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _roomId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRoomId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _roomId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PersistedRoom.class;
	}

	@Override
	public String getModelClassName() {
		return PersistedRoom.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<PersistedRoom, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<PersistedRoom, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PersistedRoom, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((PersistedRoom)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<PersistedRoom, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<PersistedRoom, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(PersistedRoom)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<PersistedRoom, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<PersistedRoom, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, PersistedRoom>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			PersistedRoom.class.getClassLoader(), PersistedRoom.class,
			ModelWrapper.class);

		try {
			Constructor<PersistedRoom> constructor =
				(Constructor<PersistedRoom>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<PersistedRoom, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<PersistedRoom, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<PersistedRoom, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<PersistedRoom, Object>>();
		Map<String, BiConsumer<PersistedRoom, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<PersistedRoom, ?>>();

		attributeGetterFunctions.put("uuid", PersistedRoom::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<PersistedRoom, String>)PersistedRoom::setUuid);
		attributeGetterFunctions.put(
			"externalReferenceCode", PersistedRoom::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<PersistedRoom, String>)
				PersistedRoom::setExternalReferenceCode);
		attributeGetterFunctions.put("roomId", PersistedRoom::getRoomId);
		attributeSetterBiConsumers.put(
			"roomId",
			(BiConsumer<PersistedRoom, Long>)PersistedRoom::setRoomId);
		attributeGetterFunctions.put(
			"calendarId", PersistedRoom::getCalendarId);
		attributeSetterBiConsumers.put(
			"calendarId",
			(BiConsumer<PersistedRoom, Long>)PersistedRoom::setCalendarId);
		attributeGetterFunctions.put("name", PersistedRoom::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<PersistedRoom, String>)PersistedRoom::setName);
		attributeGetterFunctions.put("photoPath", PersistedRoom::getPhotoPath);
		attributeSetterBiConsumers.put(
			"photoPath",
			(BiConsumer<PersistedRoom, String>)PersistedRoom::setPhotoPath);
		attributeGetterFunctions.put(
			"availableMonday", PersistedRoom::getAvailableMonday);
		attributeSetterBiConsumers.put(
			"availableMonday",
			(BiConsumer<PersistedRoom, Boolean>)
				PersistedRoom::setAvailableMonday);
		attributeGetterFunctions.put(
			"availableTuesday", PersistedRoom::getAvailableTuesday);
		attributeSetterBiConsumers.put(
			"availableTuesday",
			(BiConsumer<PersistedRoom, Boolean>)
				PersistedRoom::setAvailableTuesday);
		attributeGetterFunctions.put(
			"availableWednesday", PersistedRoom::getAvailableWednesday);
		attributeSetterBiConsumers.put(
			"availableWednesday",
			(BiConsumer<PersistedRoom, Boolean>)
				PersistedRoom::setAvailableWednesday);
		attributeGetterFunctions.put(
			"availableThursday", PersistedRoom::getAvailableThursday);
		attributeSetterBiConsumers.put(
			"availableThursday",
			(BiConsumer<PersistedRoom, Boolean>)
				PersistedRoom::setAvailableThursday);
		attributeGetterFunctions.put(
			"availableFriday", PersistedRoom::getAvailableFriday);
		attributeSetterBiConsumers.put(
			"availableFriday",
			(BiConsumer<PersistedRoom, Boolean>)
				PersistedRoom::setAvailableFriday);
		attributeGetterFunctions.put(
			"availableSaturday", PersistedRoom::getAvailableSaturday);
		attributeSetterBiConsumers.put(
			"availableSaturday",
			(BiConsumer<PersistedRoom, Boolean>)
				PersistedRoom::setAvailableSaturday);
		attributeGetterFunctions.put(
			"availableSunday", PersistedRoom::getAvailableSunday);
		attributeSetterBiConsumers.put(
			"availableSunday",
			(BiConsumer<PersistedRoom, Boolean>)
				PersistedRoom::setAvailableSunday);
		attributeGetterFunctions.put(
			"availableStartTime", PersistedRoom::getAvailableStartTime);
		attributeSetterBiConsumers.put(
			"availableStartTime",
			(BiConsumer<PersistedRoom, Integer>)
				PersistedRoom::setAvailableStartTime);
		attributeGetterFunctions.put(
			"availableEndTime", PersistedRoom::getAvailableEndTime);
		attributeSetterBiConsumers.put(
			"availableEndTime",
			(BiConsumer<PersistedRoom, Integer>)
				PersistedRoom::setAvailableEndTime);
		attributeGetterFunctions.put("officeId", PersistedRoom::getOfficeId);
		attributeSetterBiConsumers.put(
			"officeId",
			(BiConsumer<PersistedRoom, Long>)PersistedRoom::setOfficeId);
		attributeGetterFunctions.put(
			"capacitySquareMeters", PersistedRoom::getCapacitySquareMeters);
		attributeSetterBiConsumers.put(
			"capacitySquareMeters",
			(BiConsumer<PersistedRoom, Double>)
				PersistedRoom::setCapacitySquareMeters);
		attributeGetterFunctions.put(
			"capacityPeople", PersistedRoom::getCapacityPeople);
		attributeSetterBiConsumers.put(
			"capacityPeople",
			(BiConsumer<PersistedRoom, Integer>)
				PersistedRoom::setCapacityPeople);
		attributeGetterFunctions.put(
			"phoneExtension", PersistedRoom::getPhoneExtension);
		attributeSetterBiConsumers.put(
			"phoneExtension",
			(BiConsumer<PersistedRoom, Integer>)
				PersistedRoom::setPhoneExtension);
		attributeGetterFunctions.put("wifiSSID", PersistedRoom::getWifiSSID);
		attributeSetterBiConsumers.put(
			"wifiSSID",
			(BiConsumer<PersistedRoom, String>)PersistedRoom::setWifiSSID);
		attributeGetterFunctions.put(
			"wifiSecurityType", PersistedRoom::getWifiSecurityType);
		attributeSetterBiConsumers.put(
			"wifiSecurityType",
			(BiConsumer<PersistedRoom, Integer>)
				PersistedRoom::setWifiSecurityType);
		attributeGetterFunctions.put(
			"wifiPassword", PersistedRoom::getWifiPassword);
		attributeSetterBiConsumers.put(
			"wifiPassword",
			(BiConsumer<PersistedRoom, String>)PersistedRoom::setWifiPassword);
		attributeGetterFunctions.put("companyId", PersistedRoom::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<PersistedRoom, Long>)PersistedRoom::setCompanyId);
		attributeGetterFunctions.put("userId", PersistedRoom::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<PersistedRoom, Long>)PersistedRoom::setUserId);
		attributeGetterFunctions.put("userName", PersistedRoom::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<PersistedRoom, String>)PersistedRoom::setUserName);
		attributeGetterFunctions.put(
			"createDate", PersistedRoom::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<PersistedRoom, Date>)PersistedRoom::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", PersistedRoom::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<PersistedRoom, Date>)PersistedRoom::setModifiedDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		_columnBitmask |= EXTERNALREFERENCECODE_COLUMN_BITMASK;

		if (_originalExternalReferenceCode == null) {
			_originalExternalReferenceCode = _externalReferenceCode;
		}

		_externalReferenceCode = externalReferenceCode;
	}

	public String getOriginalExternalReferenceCode() {
		return GetterUtil.getString(_originalExternalReferenceCode);
	}

	@JSON
	@Override
	public long getRoomId() {
		return _roomId;
	}

	@Override
	public void setRoomId(long roomId) {
		_roomId = roomId;
	}

	@JSON
	@Override
	public long getCalendarId() {
		return _calendarId;
	}

	@Override
	public void setCalendarId(long calendarId) {
		_columnBitmask |= CALENDARID_COLUMN_BITMASK;

		if (!_setOriginalCalendarId) {
			_setOriginalCalendarId = true;

			_originalCalendarId = _calendarId;
		}

		_calendarId = calendarId;
	}

	public long getOriginalCalendarId() {
		return _originalCalendarId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public String getPhotoPath() {
		if (_photoPath == null) {
			return "";
		}
		else {
			return _photoPath;
		}
	}

	@Override
	public void setPhotoPath(String photoPath) {
		_photoPath = photoPath;
	}

	@JSON
	@Override
	public boolean getAvailableMonday() {
		return _availableMonday;
	}

	@JSON
	@Override
	public boolean isAvailableMonday() {
		return _availableMonday;
	}

	@Override
	public void setAvailableMonday(boolean availableMonday) {
		_availableMonday = availableMonday;
	}

	@JSON
	@Override
	public boolean getAvailableTuesday() {
		return _availableTuesday;
	}

	@JSON
	@Override
	public boolean isAvailableTuesday() {
		return _availableTuesday;
	}

	@Override
	public void setAvailableTuesday(boolean availableTuesday) {
		_availableTuesday = availableTuesday;
	}

	@JSON
	@Override
	public boolean getAvailableWednesday() {
		return _availableWednesday;
	}

	@JSON
	@Override
	public boolean isAvailableWednesday() {
		return _availableWednesday;
	}

	@Override
	public void setAvailableWednesday(boolean availableWednesday) {
		_availableWednesday = availableWednesday;
	}

	@JSON
	@Override
	public boolean getAvailableThursday() {
		return _availableThursday;
	}

	@JSON
	@Override
	public boolean isAvailableThursday() {
		return _availableThursday;
	}

	@Override
	public void setAvailableThursday(boolean availableThursday) {
		_availableThursday = availableThursday;
	}

	@JSON
	@Override
	public boolean getAvailableFriday() {
		return _availableFriday;
	}

	@JSON
	@Override
	public boolean isAvailableFriday() {
		return _availableFriday;
	}

	@Override
	public void setAvailableFriday(boolean availableFriday) {
		_availableFriday = availableFriday;
	}

	@JSON
	@Override
	public boolean getAvailableSaturday() {
		return _availableSaturday;
	}

	@JSON
	@Override
	public boolean isAvailableSaturday() {
		return _availableSaturday;
	}

	@Override
	public void setAvailableSaturday(boolean availableSaturday) {
		_availableSaturday = availableSaturday;
	}

	@JSON
	@Override
	public boolean getAvailableSunday() {
		return _availableSunday;
	}

	@JSON
	@Override
	public boolean isAvailableSunday() {
		return _availableSunday;
	}

	@Override
	public void setAvailableSunday(boolean availableSunday) {
		_availableSunday = availableSunday;
	}

	@JSON
	@Override
	public int getAvailableStartTime() {
		return _availableStartTime;
	}

	@Override
	public void setAvailableStartTime(int availableStartTime) {
		_availableStartTime = availableStartTime;
	}

	@JSON
	@Override
	public int getAvailableEndTime() {
		return _availableEndTime;
	}

	@Override
	public void setAvailableEndTime(int availableEndTime) {
		_availableEndTime = availableEndTime;
	}

	@JSON
	@Override
	public long getOfficeId() {
		return _officeId;
	}

	@Override
	public void setOfficeId(long officeId) {
		_columnBitmask |= OFFICEID_COLUMN_BITMASK;

		if (!_setOriginalOfficeId) {
			_setOriginalOfficeId = true;

			_originalOfficeId = _officeId;
		}

		_officeId = officeId;
	}

	public long getOriginalOfficeId() {
		return _originalOfficeId;
	}

	@JSON
	@Override
	public double getCapacitySquareMeters() {
		return _capacitySquareMeters;
	}

	@Override
	public void setCapacitySquareMeters(double capacitySquareMeters) {
		_capacitySquareMeters = capacitySquareMeters;
	}

	@JSON
	@Override
	public int getCapacityPeople() {
		return _capacityPeople;
	}

	@Override
	public void setCapacityPeople(int capacityPeople) {
		_capacityPeople = capacityPeople;
	}

	@JSON
	@Override
	public int getPhoneExtension() {
		return _phoneExtension;
	}

	@Override
	public void setPhoneExtension(int phoneExtension) {
		_phoneExtension = phoneExtension;
	}

	@JSON
	@Override
	public String getWifiSSID() {
		if (_wifiSSID == null) {
			return "";
		}
		else {
			return _wifiSSID;
		}
	}

	@Override
	public void setWifiSSID(String wifiSSID) {
		_wifiSSID = wifiSSID;
	}

	@JSON
	@Override
	public int getWifiSecurityType() {
		return _wifiSecurityType;
	}

	@Override
	public void setWifiSecurityType(int wifiSecurityType) {
		_wifiSecurityType = wifiSecurityType;
	}

	@JSON
	@Override
	public String getWifiPassword() {
		if (_wifiPassword == null) {
			return "";
		}
		else {
			return _wifiPassword;
		}
	}

	@Override
	public void setWifiPassword(String wifiPassword) {
		_wifiPassword = wifiPassword;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(PersistedRoom.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), PersistedRoom.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PersistedRoom toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, PersistedRoom>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		PersistedRoomImpl persistedRoomImpl = new PersistedRoomImpl();

		persistedRoomImpl.setUuid(getUuid());
		persistedRoomImpl.setExternalReferenceCode(getExternalReferenceCode());
		persistedRoomImpl.setRoomId(getRoomId());
		persistedRoomImpl.setCalendarId(getCalendarId());
		persistedRoomImpl.setName(getName());
		persistedRoomImpl.setPhotoPath(getPhotoPath());
		persistedRoomImpl.setAvailableMonday(isAvailableMonday());
		persistedRoomImpl.setAvailableTuesday(isAvailableTuesday());
		persistedRoomImpl.setAvailableWednesday(isAvailableWednesday());
		persistedRoomImpl.setAvailableThursday(isAvailableThursday());
		persistedRoomImpl.setAvailableFriday(isAvailableFriday());
		persistedRoomImpl.setAvailableSaturday(isAvailableSaturday());
		persistedRoomImpl.setAvailableSunday(isAvailableSunday());
		persistedRoomImpl.setAvailableStartTime(getAvailableStartTime());
		persistedRoomImpl.setAvailableEndTime(getAvailableEndTime());
		persistedRoomImpl.setOfficeId(getOfficeId());
		persistedRoomImpl.setCapacitySquareMeters(getCapacitySquareMeters());
		persistedRoomImpl.setCapacityPeople(getCapacityPeople());
		persistedRoomImpl.setPhoneExtension(getPhoneExtension());
		persistedRoomImpl.setWifiSSID(getWifiSSID());
		persistedRoomImpl.setWifiSecurityType(getWifiSecurityType());
		persistedRoomImpl.setWifiPassword(getWifiPassword());
		persistedRoomImpl.setCompanyId(getCompanyId());
		persistedRoomImpl.setUserId(getUserId());
		persistedRoomImpl.setUserName(getUserName());
		persistedRoomImpl.setCreateDate(getCreateDate());
		persistedRoomImpl.setModifiedDate(getModifiedDate());

		persistedRoomImpl.resetOriginalValues();

		return persistedRoomImpl;
	}

	@Override
	public int compareTo(PersistedRoom persistedRoom) {
		long primaryKey = persistedRoom.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PersistedRoom)) {
			return false;
		}

		PersistedRoom persistedRoom = (PersistedRoom)obj;

		long primaryKey = persistedRoom.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		PersistedRoomModelImpl persistedRoomModelImpl = this;

		persistedRoomModelImpl._originalUuid = persistedRoomModelImpl._uuid;

		persistedRoomModelImpl._originalExternalReferenceCode =
			persistedRoomModelImpl._externalReferenceCode;

		persistedRoomModelImpl._originalCalendarId =
			persistedRoomModelImpl._calendarId;

		persistedRoomModelImpl._setOriginalCalendarId = false;

		persistedRoomModelImpl._originalOfficeId =
			persistedRoomModelImpl._officeId;

		persistedRoomModelImpl._setOriginalOfficeId = false;

		persistedRoomModelImpl._originalCompanyId =
			persistedRoomModelImpl._companyId;

		persistedRoomModelImpl._setOriginalCompanyId = false;

		persistedRoomModelImpl._setModifiedDate = false;

		persistedRoomModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<PersistedRoom> toCacheModel() {
		PersistedRoomCacheModel persistedRoomCacheModel =
			new PersistedRoomCacheModel();

		persistedRoomCacheModel.uuid = getUuid();

		String uuid = persistedRoomCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			persistedRoomCacheModel.uuid = null;
		}

		persistedRoomCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			persistedRoomCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			persistedRoomCacheModel.externalReferenceCode = null;
		}

		persistedRoomCacheModel.roomId = getRoomId();

		persistedRoomCacheModel.calendarId = getCalendarId();

		persistedRoomCacheModel.name = getName();

		String name = persistedRoomCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			persistedRoomCacheModel.name = null;
		}

		persistedRoomCacheModel.photoPath = getPhotoPath();

		String photoPath = persistedRoomCacheModel.photoPath;

		if ((photoPath != null) && (photoPath.length() == 0)) {
			persistedRoomCacheModel.photoPath = null;
		}

		persistedRoomCacheModel.availableMonday = isAvailableMonday();

		persistedRoomCacheModel.availableTuesday = isAvailableTuesday();

		persistedRoomCacheModel.availableWednesday = isAvailableWednesday();

		persistedRoomCacheModel.availableThursday = isAvailableThursday();

		persistedRoomCacheModel.availableFriday = isAvailableFriday();

		persistedRoomCacheModel.availableSaturday = isAvailableSaturday();

		persistedRoomCacheModel.availableSunday = isAvailableSunday();

		persistedRoomCacheModel.availableStartTime = getAvailableStartTime();

		persistedRoomCacheModel.availableEndTime = getAvailableEndTime();

		persistedRoomCacheModel.officeId = getOfficeId();

		persistedRoomCacheModel.capacitySquareMeters =
			getCapacitySquareMeters();

		persistedRoomCacheModel.capacityPeople = getCapacityPeople();

		persistedRoomCacheModel.phoneExtension = getPhoneExtension();

		persistedRoomCacheModel.wifiSSID = getWifiSSID();

		String wifiSSID = persistedRoomCacheModel.wifiSSID;

		if ((wifiSSID != null) && (wifiSSID.length() == 0)) {
			persistedRoomCacheModel.wifiSSID = null;
		}

		persistedRoomCacheModel.wifiSecurityType = getWifiSecurityType();

		persistedRoomCacheModel.wifiPassword = getWifiPassword();

		String wifiPassword = persistedRoomCacheModel.wifiPassword;

		if ((wifiPassword != null) && (wifiPassword.length() == 0)) {
			persistedRoomCacheModel.wifiPassword = null;
		}

		persistedRoomCacheModel.companyId = getCompanyId();

		persistedRoomCacheModel.userId = getUserId();

		persistedRoomCacheModel.userName = getUserName();

		String userName = persistedRoomCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			persistedRoomCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			persistedRoomCacheModel.createDate = createDate.getTime();
		}
		else {
			persistedRoomCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			persistedRoomCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			persistedRoomCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		return persistedRoomCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<PersistedRoom, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<PersistedRoom, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PersistedRoom, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((PersistedRoom)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<PersistedRoom, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<PersistedRoom, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PersistedRoom, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((PersistedRoom)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, PersistedRoom>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private String _externalReferenceCode;
	private String _originalExternalReferenceCode;
	private long _roomId;
	private long _calendarId;
	private long _originalCalendarId;
	private boolean _setOriginalCalendarId;
	private String _name;
	private String _photoPath;
	private boolean _availableMonday;
	private boolean _availableTuesday;
	private boolean _availableWednesday;
	private boolean _availableThursday;
	private boolean _availableFriday;
	private boolean _availableSaturday;
	private boolean _availableSunday;
	private int _availableStartTime;
	private int _availableEndTime;
	private long _officeId;
	private long _originalOfficeId;
	private boolean _setOriginalOfficeId;
	private double _capacitySquareMeters;
	private int _capacityPeople;
	private int _phoneExtension;
	private String _wifiSSID;
	private int _wifiSecurityType;
	private String _wifiPassword;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _columnBitmask;
	private PersistedRoom _escapedModel;

}