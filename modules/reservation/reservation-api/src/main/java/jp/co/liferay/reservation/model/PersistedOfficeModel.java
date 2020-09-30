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

package jp.co.liferay.reservation.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the PersistedOffice service. Represents a row in the &quot;Reservation_PersistedOffice&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>jp.co.liferay.reservation.model.impl.PersistedOfficeModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>jp.co.liferay.reservation.model.impl.PersistedOfficeImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PersistedOffice
 * @generated
 */
@ProviderType
public interface PersistedOfficeModel
	extends BaseModel<PersistedOffice>, ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a persisted office model instance should use the {@link PersistedOffice} interface instead.
	 */

	/**
	 * Returns the primary key of this persisted office.
	 *
	 * @return the primary key of this persisted office
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this persisted office.
	 *
	 * @param primaryKey the primary key of this persisted office
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this persisted office.
	 *
	 * @return the uuid of this persisted office
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this persisted office.
	 *
	 * @param uuid the uuid of this persisted office
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this persisted office.
	 *
	 * @return the external reference code of this persisted office
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this persisted office.
	 *
	 * @param externalReferenceCode the external reference code of this persisted office
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the office ID of this persisted office.
	 *
	 * @return the office ID of this persisted office
	 */
	public long getOfficeId();

	/**
	 * Sets the office ID of this persisted office.
	 *
	 * @param officeId the office ID of this persisted office
	 */
	public void setOfficeId(long officeId);

	/**
	 * Returns the calendar resource ID of this persisted office.
	 *
	 * @return the calendar resource ID of this persisted office
	 */
	public long getCalendarResourceId();

	/**
	 * Sets the calendar resource ID of this persisted office.
	 *
	 * @param calendarResourceId the calendar resource ID of this persisted office
	 */
	public void setCalendarResourceId(long calendarResourceId);

	/**
	 * Returns the name of this persisted office.
	 *
	 * @return the name of this persisted office
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this persisted office.
	 *
	 * @param name the name of this persisted office
	 */
	public void setName(String name);

	/**
	 * Returns the location of this persisted office.
	 *
	 * @return the location of this persisted office
	 */
	@AutoEscape
	public String getLocation();

	/**
	 * Sets the location of this persisted office.
	 *
	 * @param location the location of this persisted office
	 */
	public void setLocation(String location);

	/**
	 * Returns the room count of this persisted office.
	 *
	 * @return the room count of this persisted office
	 */
	public int getRoomCount();

	/**
	 * Sets the room count of this persisted office.
	 *
	 * @param roomCount the room count of this persisted office
	 */
	public void setRoomCount(int roomCount);

	/**
	 * Returns the company ID of this persisted office.
	 *
	 * @return the company ID of this persisted office
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this persisted office.
	 *
	 * @param companyId the company ID of this persisted office
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this persisted office.
	 *
	 * @return the user ID of this persisted office
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this persisted office.
	 *
	 * @param userId the user ID of this persisted office
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this persisted office.
	 *
	 * @return the user uuid of this persisted office
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this persisted office.
	 *
	 * @param userUuid the user uuid of this persisted office
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this persisted office.
	 *
	 * @return the user name of this persisted office
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this persisted office.
	 *
	 * @param userName the user name of this persisted office
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this persisted office.
	 *
	 * @return the create date of this persisted office
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this persisted office.
	 *
	 * @param createDate the create date of this persisted office
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this persisted office.
	 *
	 * @return the modified date of this persisted office
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this persisted office.
	 *
	 * @param modifiedDate the modified date of this persisted office
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

}