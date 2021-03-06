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

import jp.co.liferay.reservation.model.PersistedBooking;
import jp.co.liferay.reservation.model.PersistedBookingModel;
import jp.co.liferay.reservation.model.PersistedBookingSoap;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the PersistedBooking service. Represents a row in the &quot;Reservation_PersistedBooking&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>PersistedBookingModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PersistedBookingImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PersistedBookingImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class PersistedBookingModelImpl
	extends BaseModelImpl<PersistedBooking> implements PersistedBookingModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a persisted booking model instance should use the <code>PersistedBooking</code> interface instead.
	 */
	public static final String TABLE_NAME = "Reservation_PersistedBooking";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"externalReferenceCode", Types.VARCHAR},
		{"bookingId", Types.BIGINT}, {"calendarBookingId", Types.BIGINT},
		{"title", Types.VARCHAR}, {"startDate", Types.TIMESTAMP},
		{"endDate", Types.TIMESTAMP}, {"roomId", Types.BIGINT},
		{"description", Types.VARCHAR}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("bookingId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("calendarBookingId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("roomId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Reservation_PersistedBooking (uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,bookingId LONG not null primary key,calendarBookingId LONG,title VARCHAR(75) null,startDate DATE null,endDate DATE null,roomId LONG,description VARCHAR(75) null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table Reservation_PersistedBooking";

	public static final String ORDER_BY_JPQL =
		" ORDER BY persistedBooking.bookingId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Reservation_PersistedBooking.bookingId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 2L;

	public static final long ROOMID_COLUMN_BITMASK = 4L;

	public static final long USERID_COLUMN_BITMASK = 8L;

	public static final long UUID_COLUMN_BITMASK = 16L;

	public static final long BOOKINGID_COLUMN_BITMASK = 32L;

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
	public static PersistedBooking toModel(PersistedBookingSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		PersistedBooking model = new PersistedBookingImpl();

		model.setUuid(soapModel.getUuid());
		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setBookingId(soapModel.getBookingId());
		model.setCalendarBookingId(soapModel.getCalendarBookingId());
		model.setTitle(soapModel.getTitle());
		model.setStartDate(soapModel.getStartDate());
		model.setEndDate(soapModel.getEndDate());
		model.setRoomId(soapModel.getRoomId());
		model.setDescription(soapModel.getDescription());
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
	public static List<PersistedBooking> toModels(
		PersistedBookingSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<PersistedBooking> models = new ArrayList<PersistedBooking>(
			soapModels.length);

		for (PersistedBookingSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final String
		MAPPING_TABLE_RESERVATION_PARTICIPANTS_BOOKINGS_NAME =
			"Reservation_Participants_Bookings";

	public static final Object[][]
		MAPPING_TABLE_RESERVATION_PARTICIPANTS_BOOKINGS_COLUMNS = {
			{"companyId", Types.BIGINT}, {"bookingId", Types.BIGINT},
			{"participantId", Types.BIGINT}
		};

	public static final String
		MAPPING_TABLE_RESERVATION_PARTICIPANTS_BOOKINGS_SQL_CREATE =
			"create table Reservation_Participants_Bookings (companyId LONG not null,bookingId LONG not null,participantId LONG not null,primary key (bookingId, participantId))";

	public PersistedBookingModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _bookingId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBookingId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _bookingId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PersistedBooking.class;
	}

	@Override
	public String getModelClassName() {
		return PersistedBooking.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<PersistedBooking, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<PersistedBooking, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PersistedBooking, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((PersistedBooking)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<PersistedBooking, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<PersistedBooking, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(PersistedBooking)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<PersistedBooking, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<PersistedBooking, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, PersistedBooking>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			PersistedBooking.class.getClassLoader(), PersistedBooking.class,
			ModelWrapper.class);

		try {
			Constructor<PersistedBooking> constructor =
				(Constructor<PersistedBooking>)proxyClass.getConstructor(
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

	private static final Map<String, Function<PersistedBooking, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<PersistedBooking, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<PersistedBooking, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<PersistedBooking, Object>>();
		Map<String, BiConsumer<PersistedBooking, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<PersistedBooking, ?>>();

		attributeGetterFunctions.put("uuid", PersistedBooking::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<PersistedBooking, String>)PersistedBooking::setUuid);
		attributeGetterFunctions.put(
			"externalReferenceCode",
			PersistedBooking::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<PersistedBooking, String>)
				PersistedBooking::setExternalReferenceCode);
		attributeGetterFunctions.put(
			"bookingId", PersistedBooking::getBookingId);
		attributeSetterBiConsumers.put(
			"bookingId",
			(BiConsumer<PersistedBooking, Long>)PersistedBooking::setBookingId);
		attributeGetterFunctions.put(
			"calendarBookingId", PersistedBooking::getCalendarBookingId);
		attributeSetterBiConsumers.put(
			"calendarBookingId",
			(BiConsumer<PersistedBooking, Long>)
				PersistedBooking::setCalendarBookingId);
		attributeGetterFunctions.put("title", PersistedBooking::getTitle);
		attributeSetterBiConsumers.put(
			"title",
			(BiConsumer<PersistedBooking, String>)PersistedBooking::setTitle);
		attributeGetterFunctions.put(
			"startDate", PersistedBooking::getStartDate);
		attributeSetterBiConsumers.put(
			"startDate",
			(BiConsumer<PersistedBooking, Date>)PersistedBooking::setStartDate);
		attributeGetterFunctions.put("endDate", PersistedBooking::getEndDate);
		attributeSetterBiConsumers.put(
			"endDate",
			(BiConsumer<PersistedBooking, Date>)PersistedBooking::setEndDate);
		attributeGetterFunctions.put("roomId", PersistedBooking::getRoomId);
		attributeSetterBiConsumers.put(
			"roomId",
			(BiConsumer<PersistedBooking, Long>)PersistedBooking::setRoomId);
		attributeGetterFunctions.put(
			"description", PersistedBooking::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<PersistedBooking, String>)
				PersistedBooking::setDescription);
		attributeGetterFunctions.put(
			"companyId", PersistedBooking::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<PersistedBooking, Long>)PersistedBooking::setCompanyId);
		attributeGetterFunctions.put("userId", PersistedBooking::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<PersistedBooking, Long>)PersistedBooking::setUserId);
		attributeGetterFunctions.put("userName", PersistedBooking::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<PersistedBooking, String>)
				PersistedBooking::setUserName);
		attributeGetterFunctions.put(
			"createDate", PersistedBooking::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<PersistedBooking, Date>)
				PersistedBooking::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", PersistedBooking::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<PersistedBooking, Date>)
				PersistedBooking::setModifiedDate);

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
	public long getBookingId() {
		return _bookingId;
	}

	@Override
	public void setBookingId(long bookingId) {
		_bookingId = bookingId;
	}

	@JSON
	@Override
	public long getCalendarBookingId() {
		return _calendarBookingId;
	}

	@Override
	public void setCalendarBookingId(long calendarBookingId) {
		_calendarBookingId = calendarBookingId;
	}

	@JSON
	@Override
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		_title = title;
	}

	@JSON
	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@JSON
	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	@JSON
	@Override
	public long getRoomId() {
		return _roomId;
	}

	@Override
	public void setRoomId(long roomId) {
		_columnBitmask |= ROOMID_COLUMN_BITMASK;

		if (!_setOriginalRoomId) {
			_setOriginalRoomId = true;

			_originalRoomId = _roomId;
		}

		_roomId = roomId;
	}

	public long getOriginalRoomId() {
		return _originalRoomId;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
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
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

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

	public long getOriginalUserId() {
		return _originalUserId;
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
			PortalUtil.getClassNameId(PersistedBooking.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), PersistedBooking.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PersistedBooking toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, PersistedBooking>
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
		PersistedBookingImpl persistedBookingImpl = new PersistedBookingImpl();

		persistedBookingImpl.setUuid(getUuid());
		persistedBookingImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		persistedBookingImpl.setBookingId(getBookingId());
		persistedBookingImpl.setCalendarBookingId(getCalendarBookingId());
		persistedBookingImpl.setTitle(getTitle());
		persistedBookingImpl.setStartDate(getStartDate());
		persistedBookingImpl.setEndDate(getEndDate());
		persistedBookingImpl.setRoomId(getRoomId());
		persistedBookingImpl.setDescription(getDescription());
		persistedBookingImpl.setCompanyId(getCompanyId());
		persistedBookingImpl.setUserId(getUserId());
		persistedBookingImpl.setUserName(getUserName());
		persistedBookingImpl.setCreateDate(getCreateDate());
		persistedBookingImpl.setModifiedDate(getModifiedDate());

		persistedBookingImpl.resetOriginalValues();

		return persistedBookingImpl;
	}

	@Override
	public int compareTo(PersistedBooking persistedBooking) {
		long primaryKey = persistedBooking.getPrimaryKey();

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

		if (!(obj instanceof PersistedBooking)) {
			return false;
		}

		PersistedBooking persistedBooking = (PersistedBooking)obj;

		long primaryKey = persistedBooking.getPrimaryKey();

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
		PersistedBookingModelImpl persistedBookingModelImpl = this;

		persistedBookingModelImpl._originalUuid =
			persistedBookingModelImpl._uuid;

		persistedBookingModelImpl._originalExternalReferenceCode =
			persistedBookingModelImpl._externalReferenceCode;

		persistedBookingModelImpl._originalRoomId =
			persistedBookingModelImpl._roomId;

		persistedBookingModelImpl._setOriginalRoomId = false;

		persistedBookingModelImpl._originalCompanyId =
			persistedBookingModelImpl._companyId;

		persistedBookingModelImpl._setOriginalCompanyId = false;

		persistedBookingModelImpl._originalUserId =
			persistedBookingModelImpl._userId;

		persistedBookingModelImpl._setOriginalUserId = false;

		persistedBookingModelImpl._setModifiedDate = false;

		persistedBookingModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<PersistedBooking> toCacheModel() {
		PersistedBookingCacheModel persistedBookingCacheModel =
			new PersistedBookingCacheModel();

		persistedBookingCacheModel.uuid = getUuid();

		String uuid = persistedBookingCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			persistedBookingCacheModel.uuid = null;
		}

		persistedBookingCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			persistedBookingCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			persistedBookingCacheModel.externalReferenceCode = null;
		}

		persistedBookingCacheModel.bookingId = getBookingId();

		persistedBookingCacheModel.calendarBookingId = getCalendarBookingId();

		persistedBookingCacheModel.title = getTitle();

		String title = persistedBookingCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			persistedBookingCacheModel.title = null;
		}

		Date startDate = getStartDate();

		if (startDate != null) {
			persistedBookingCacheModel.startDate = startDate.getTime();
		}
		else {
			persistedBookingCacheModel.startDate = Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			persistedBookingCacheModel.endDate = endDate.getTime();
		}
		else {
			persistedBookingCacheModel.endDate = Long.MIN_VALUE;
		}

		persistedBookingCacheModel.roomId = getRoomId();

		persistedBookingCacheModel.description = getDescription();

		String description = persistedBookingCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			persistedBookingCacheModel.description = null;
		}

		persistedBookingCacheModel.companyId = getCompanyId();

		persistedBookingCacheModel.userId = getUserId();

		persistedBookingCacheModel.userName = getUserName();

		String userName = persistedBookingCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			persistedBookingCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			persistedBookingCacheModel.createDate = createDate.getTime();
		}
		else {
			persistedBookingCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			persistedBookingCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			persistedBookingCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		return persistedBookingCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<PersistedBooking, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<PersistedBooking, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PersistedBooking, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((PersistedBooking)this));
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
		Map<String, Function<PersistedBooking, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<PersistedBooking, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PersistedBooking, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((PersistedBooking)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, PersistedBooking>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private String _externalReferenceCode;
	private String _originalExternalReferenceCode;
	private long _bookingId;
	private long _calendarBookingId;
	private String _title;
	private Date _startDate;
	private Date _endDate;
	private long _roomId;
	private long _originalRoomId;
	private boolean _setOriginalRoomId;
	private String _description;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _columnBitmask;
	private PersistedBooking _escapedModel;

}