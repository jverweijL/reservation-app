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

import jp.co.liferay.reservation.model.PersistedOffice;
import jp.co.liferay.reservation.model.PersistedOfficeModel;
import jp.co.liferay.reservation.model.PersistedOfficeSoap;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the PersistedOffice service. Represents a row in the &quot;Reservation_PersistedOffice&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>PersistedOfficeModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PersistedOfficeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PersistedOfficeImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class PersistedOfficeModelImpl
	extends BaseModelImpl<PersistedOffice> implements PersistedOfficeModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a persisted office model instance should use the <code>PersistedOffice</code> interface instead.
	 */
	public static final String TABLE_NAME = "Reservation_PersistedOffice";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"externalReferenceCode", Types.VARCHAR},
		{"officeId", Types.BIGINT}, {"calendarResourceId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"location", Types.VARCHAR},
		{"roomCount", Types.INTEGER}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("officeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("calendarResourceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("location", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("roomCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Reservation_PersistedOffice (uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,officeId LONG not null primary key,calendarResourceId LONG,name VARCHAR(75) null,location VARCHAR(75) null,roomCount INTEGER,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null)";

	public static final String TABLE_SQL_DROP =
		"drop table Reservation_PersistedOffice";

	public static final String ORDER_BY_JPQL =
		" ORDER BY persistedOffice.officeId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Reservation_PersistedOffice.officeId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static final long OFFICEID_COLUMN_BITMASK = 8L;

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
	public static PersistedOffice toModel(PersistedOfficeSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		PersistedOffice model = new PersistedOfficeImpl();

		model.setUuid(soapModel.getUuid());
		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setOfficeId(soapModel.getOfficeId());
		model.setCalendarResourceId(soapModel.getCalendarResourceId());
		model.setName(soapModel.getName());
		model.setLocation(soapModel.getLocation());
		model.setRoomCount(soapModel.getRoomCount());
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
	public static List<PersistedOffice> toModels(
		PersistedOfficeSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<PersistedOffice> models = new ArrayList<PersistedOffice>(
			soapModels.length);

		for (PersistedOfficeSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public PersistedOfficeModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _officeId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOfficeId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _officeId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PersistedOffice.class;
	}

	@Override
	public String getModelClassName() {
		return PersistedOffice.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<PersistedOffice, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<PersistedOffice, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PersistedOffice, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((PersistedOffice)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<PersistedOffice, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<PersistedOffice, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(PersistedOffice)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<PersistedOffice, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<PersistedOffice, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, PersistedOffice>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			PersistedOffice.class.getClassLoader(), PersistedOffice.class,
			ModelWrapper.class);

		try {
			Constructor<PersistedOffice> constructor =
				(Constructor<PersistedOffice>)proxyClass.getConstructor(
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

	private static final Map<String, Function<PersistedOffice, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<PersistedOffice, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<PersistedOffice, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<PersistedOffice, Object>>();
		Map<String, BiConsumer<PersistedOffice, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<PersistedOffice, ?>>();

		attributeGetterFunctions.put("uuid", PersistedOffice::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<PersistedOffice, String>)PersistedOffice::setUuid);
		attributeGetterFunctions.put(
			"externalReferenceCode", PersistedOffice::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<PersistedOffice, String>)
				PersistedOffice::setExternalReferenceCode);
		attributeGetterFunctions.put("officeId", PersistedOffice::getOfficeId);
		attributeSetterBiConsumers.put(
			"officeId",
			(BiConsumer<PersistedOffice, Long>)PersistedOffice::setOfficeId);
		attributeGetterFunctions.put(
			"calendarResourceId", PersistedOffice::getCalendarResourceId);
		attributeSetterBiConsumers.put(
			"calendarResourceId",
			(BiConsumer<PersistedOffice, Long>)
				PersistedOffice::setCalendarResourceId);
		attributeGetterFunctions.put("name", PersistedOffice::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<PersistedOffice, String>)PersistedOffice::setName);
		attributeGetterFunctions.put("location", PersistedOffice::getLocation);
		attributeSetterBiConsumers.put(
			"location",
			(BiConsumer<PersistedOffice, String>)PersistedOffice::setLocation);
		attributeGetterFunctions.put(
			"roomCount", PersistedOffice::getRoomCount);
		attributeSetterBiConsumers.put(
			"roomCount",
			(BiConsumer<PersistedOffice, Integer>)
				PersistedOffice::setRoomCount);
		attributeGetterFunctions.put(
			"companyId", PersistedOffice::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<PersistedOffice, Long>)PersistedOffice::setCompanyId);
		attributeGetterFunctions.put("userId", PersistedOffice::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<PersistedOffice, Long>)PersistedOffice::setUserId);
		attributeGetterFunctions.put("userName", PersistedOffice::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<PersistedOffice, String>)PersistedOffice::setUserName);
		attributeGetterFunctions.put(
			"createDate", PersistedOffice::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<PersistedOffice, Date>)PersistedOffice::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", PersistedOffice::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<PersistedOffice, Date>)
				PersistedOffice::setModifiedDate);

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
	public long getOfficeId() {
		return _officeId;
	}

	@Override
	public void setOfficeId(long officeId) {
		_officeId = officeId;
	}

	@JSON
	@Override
	public long getCalendarResourceId() {
		return _calendarResourceId;
	}

	@Override
	public void setCalendarResourceId(long calendarResourceId) {
		_calendarResourceId = calendarResourceId;
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
	public String getLocation() {
		if (_location == null) {
			return "";
		}
		else {
			return _location;
		}
	}

	@Override
	public void setLocation(String location) {
		_location = location;
	}

	@JSON
	@Override
	public int getRoomCount() {
		return _roomCount;
	}

	@Override
	public void setRoomCount(int roomCount) {
		_roomCount = roomCount;
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
			PortalUtil.getClassNameId(PersistedOffice.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), PersistedOffice.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PersistedOffice toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, PersistedOffice>
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
		PersistedOfficeImpl persistedOfficeImpl = new PersistedOfficeImpl();

		persistedOfficeImpl.setUuid(getUuid());
		persistedOfficeImpl.setExternalReferenceCode(
			getExternalReferenceCode());
		persistedOfficeImpl.setOfficeId(getOfficeId());
		persistedOfficeImpl.setCalendarResourceId(getCalendarResourceId());
		persistedOfficeImpl.setName(getName());
		persistedOfficeImpl.setLocation(getLocation());
		persistedOfficeImpl.setRoomCount(getRoomCount());
		persistedOfficeImpl.setCompanyId(getCompanyId());
		persistedOfficeImpl.setUserId(getUserId());
		persistedOfficeImpl.setUserName(getUserName());
		persistedOfficeImpl.setCreateDate(getCreateDate());
		persistedOfficeImpl.setModifiedDate(getModifiedDate());

		persistedOfficeImpl.resetOriginalValues();

		return persistedOfficeImpl;
	}

	@Override
	public int compareTo(PersistedOffice persistedOffice) {
		long primaryKey = persistedOffice.getPrimaryKey();

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

		if (!(obj instanceof PersistedOffice)) {
			return false;
		}

		PersistedOffice persistedOffice = (PersistedOffice)obj;

		long primaryKey = persistedOffice.getPrimaryKey();

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
		PersistedOfficeModelImpl persistedOfficeModelImpl = this;

		persistedOfficeModelImpl._originalUuid = persistedOfficeModelImpl._uuid;

		persistedOfficeModelImpl._originalExternalReferenceCode =
			persistedOfficeModelImpl._externalReferenceCode;

		persistedOfficeModelImpl._originalCompanyId =
			persistedOfficeModelImpl._companyId;

		persistedOfficeModelImpl._setOriginalCompanyId = false;

		persistedOfficeModelImpl._setModifiedDate = false;

		persistedOfficeModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<PersistedOffice> toCacheModel() {
		PersistedOfficeCacheModel persistedOfficeCacheModel =
			new PersistedOfficeCacheModel();

		persistedOfficeCacheModel.uuid = getUuid();

		String uuid = persistedOfficeCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			persistedOfficeCacheModel.uuid = null;
		}

		persistedOfficeCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			persistedOfficeCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			persistedOfficeCacheModel.externalReferenceCode = null;
		}

		persistedOfficeCacheModel.officeId = getOfficeId();

		persistedOfficeCacheModel.calendarResourceId = getCalendarResourceId();

		persistedOfficeCacheModel.name = getName();

		String name = persistedOfficeCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			persistedOfficeCacheModel.name = null;
		}

		persistedOfficeCacheModel.location = getLocation();

		String location = persistedOfficeCacheModel.location;

		if ((location != null) && (location.length() == 0)) {
			persistedOfficeCacheModel.location = null;
		}

		persistedOfficeCacheModel.roomCount = getRoomCount();

		persistedOfficeCacheModel.companyId = getCompanyId();

		persistedOfficeCacheModel.userId = getUserId();

		persistedOfficeCacheModel.userName = getUserName();

		String userName = persistedOfficeCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			persistedOfficeCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			persistedOfficeCacheModel.createDate = createDate.getTime();
		}
		else {
			persistedOfficeCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			persistedOfficeCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			persistedOfficeCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		return persistedOfficeCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<PersistedOffice, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<PersistedOffice, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PersistedOffice, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((PersistedOffice)this));
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
		Map<String, Function<PersistedOffice, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<PersistedOffice, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PersistedOffice, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((PersistedOffice)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, PersistedOffice>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private String _externalReferenceCode;
	private String _originalExternalReferenceCode;
	private long _officeId;
	private long _calendarResourceId;
	private String _name;
	private String _location;
	private int _roomCount;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _columnBitmask;
	private PersistedOffice _escapedModel;

}