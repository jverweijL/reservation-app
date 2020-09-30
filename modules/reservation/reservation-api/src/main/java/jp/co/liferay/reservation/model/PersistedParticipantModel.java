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

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the PersistedParticipant service. Represents a row in the &quot;Reservation_PersistedParticipant&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>jp.co.liferay.reservation.model.impl.PersistedParticipantModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>jp.co.liferay.reservation.model.impl.PersistedParticipantImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PersistedParticipant
 * @generated
 */
@ProviderType
public interface PersistedParticipantModel
	extends BaseModel<PersistedParticipant>, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a persisted participant model instance should use the {@link PersistedParticipant} interface instead.
	 */

	/**
	 * Returns the primary key of this persisted participant.
	 *
	 * @return the primary key of this persisted participant
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this persisted participant.
	 *
	 * @param primaryKey the primary key of this persisted participant
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this persisted participant.
	 *
	 * @return the uuid of this persisted participant
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this persisted participant.
	 *
	 * @param uuid the uuid of this persisted participant
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the external reference code of this persisted participant.
	 *
	 * @return the external reference code of this persisted participant
	 */
	@AutoEscape
	public String getExternalReferenceCode();

	/**
	 * Sets the external reference code of this persisted participant.
	 *
	 * @param externalReferenceCode the external reference code of this persisted participant
	 */
	public void setExternalReferenceCode(String externalReferenceCode);

	/**
	 * Returns the participant ID of this persisted participant.
	 *
	 * @return the participant ID of this persisted participant
	 */
	public long getParticipantId();

	/**
	 * Sets the participant ID of this persisted participant.
	 *
	 * @param participantId the participant ID of this persisted participant
	 */
	public void setParticipantId(long participantId);

	/**
	 * Returns the user ID of this persisted participant.
	 *
	 * @return the user ID of this persisted participant
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this persisted participant.
	 *
	 * @param userId the user ID of this persisted participant
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this persisted participant.
	 *
	 * @return the user uuid of this persisted participant
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this persisted participant.
	 *
	 * @param userUuid the user uuid of this persisted participant
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the full name of this persisted participant.
	 *
	 * @return the full name of this persisted participant
	 */
	@AutoEscape
	public String getFullName();

	/**
	 * Sets the full name of this persisted participant.
	 *
	 * @param fullName the full name of this persisted participant
	 */
	public void setFullName(String fullName);

	/**
	 * Returns the company name of this persisted participant.
	 *
	 * @return the company name of this persisted participant
	 */
	@AutoEscape
	public String getCompanyName();

	/**
	 * Sets the company name of this persisted participant.
	 *
	 * @param companyName the company name of this persisted participant
	 */
	public void setCompanyName(String companyName);

	/**
	 * Returns the email address of this persisted participant.
	 *
	 * @return the email address of this persisted participant
	 */
	@AutoEscape
	public String getEmailAddress();

	/**
	 * Sets the email address of this persisted participant.
	 *
	 * @param emailAddress the email address of this persisted participant
	 */
	public void setEmailAddress(String emailAddress);

	/**
	 * Returns the company ID of this persisted participant.
	 *
	 * @return the company ID of this persisted participant
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this persisted participant.
	 *
	 * @param companyId the company ID of this persisted participant
	 */
	@Override
	public void setCompanyId(long companyId);

}