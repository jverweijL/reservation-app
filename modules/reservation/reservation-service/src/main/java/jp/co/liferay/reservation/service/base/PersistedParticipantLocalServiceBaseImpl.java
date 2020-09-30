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

package jp.co.liferay.reservation.service.base;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import jp.co.liferay.reservation.model.PersistedParticipant;
import jp.co.liferay.reservation.service.PersistedParticipantLocalService;
import jp.co.liferay.reservation.service.persistence.PersistedAmenityPersistence;
import jp.co.liferay.reservation.service.persistence.PersistedBookingPersistence;
import jp.co.liferay.reservation.service.persistence.PersistedOfficePersistence;
import jp.co.liferay.reservation.service.persistence.PersistedParticipantPersistence;
import jp.co.liferay.reservation.service.persistence.PersistedPurposePersistence;
import jp.co.liferay.reservation.service.persistence.PersistedRoomPersistence;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the persisted participant local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link jp.co.liferay.reservation.service.impl.PersistedParticipantLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see jp.co.liferay.reservation.service.impl.PersistedParticipantLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class PersistedParticipantLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements PersistedParticipantLocalService, AopService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>PersistedParticipantLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>jp.co.liferay.reservation.service.PersistedParticipantLocalServiceUtil</code>.
	 */

	/**
	 * Adds the persisted participant to the database. Also notifies the appropriate model listeners.
	 *
	 * @param persistedParticipant the persisted participant
	 * @return the persisted participant that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PersistedParticipant addPersistedParticipant(
		PersistedParticipant persistedParticipant) {

		persistedParticipant.setNew(true);

		return persistedParticipantPersistence.update(persistedParticipant);
	}

	/**
	 * Creates a new persisted participant with the primary key. Does not add the persisted participant to the database.
	 *
	 * @param participantId the primary key for the new persisted participant
	 * @return the new persisted participant
	 */
	@Override
	@Transactional(enabled = false)
	public PersistedParticipant createPersistedParticipant(long participantId) {
		return persistedParticipantPersistence.create(participantId);
	}

	/**
	 * Deletes the persisted participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param participantId the primary key of the persisted participant
	 * @return the persisted participant that was removed
	 * @throws PortalException if a persisted participant with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PersistedParticipant deletePersistedParticipant(long participantId)
		throws PortalException {

		return persistedParticipantPersistence.remove(participantId);
	}

	/**
	 * Deletes the persisted participant from the database. Also notifies the appropriate model listeners.
	 *
	 * @param persistedParticipant the persisted participant
	 * @return the persisted participant that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PersistedParticipant deletePersistedParticipant(
		PersistedParticipant persistedParticipant) {

		return persistedParticipantPersistence.remove(persistedParticipant);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			PersistedParticipant.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return persistedParticipantPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>jp.co.liferay.reservation.model.impl.PersistedParticipantModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return persistedParticipantPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>jp.co.liferay.reservation.model.impl.PersistedParticipantModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return persistedParticipantPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return persistedParticipantPersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return persistedParticipantPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public PersistedParticipant fetchPersistedParticipant(long participantId) {
		return persistedParticipantPersistence.fetchByPrimaryKey(participantId);
	}

	/**
	 * Returns the persisted participant with the matching UUID and company.
	 *
	 * @param uuid the persisted participant's UUID
	 * @param companyId the primary key of the company
	 * @return the matching persisted participant, or <code>null</code> if a matching persisted participant could not be found
	 */
	@Override
	public PersistedParticipant fetchPersistedParticipantByUuidAndCompanyId(
		String uuid, long companyId) {

		return persistedParticipantPersistence.fetchByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns the persisted participant with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the persisted participant's external reference code
	 * @return the matching persisted participant, or <code>null</code> if a matching persisted participant could not be found
	 */
	@Override
	public PersistedParticipant fetchPersistedParticipantByReferenceCode(
		long companyId, String externalReferenceCode) {

		return persistedParticipantPersistence.fetchByC_ERC(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the persisted participant with the primary key.
	 *
	 * @param participantId the primary key of the persisted participant
	 * @return the persisted participant
	 * @throws PortalException if a persisted participant with the primary key could not be found
	 */
	@Override
	public PersistedParticipant getPersistedParticipant(long participantId)
		throws PortalException {

		return persistedParticipantPersistence.findByPrimaryKey(participantId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			persistedParticipantLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PersistedParticipant.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("participantId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			persistedParticipantLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			PersistedParticipant.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"participantId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			persistedParticipantLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PersistedParticipant.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("participantId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return persistedParticipantLocalService.deletePersistedParticipant(
			(PersistedParticipant)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return persistedParticipantPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the persisted participant with the matching UUID and company.
	 *
	 * @param uuid the persisted participant's UUID
	 * @param companyId the primary key of the company
	 * @return the matching persisted participant
	 * @throws PortalException if a matching persisted participant could not be found
	 */
	@Override
	public PersistedParticipant getPersistedParticipantByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return persistedParticipantPersistence.findByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns a range of all the persisted participants.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>jp.co.liferay.reservation.model.impl.PersistedParticipantModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of persisted participants
	 * @param end the upper bound of the range of persisted participants (not inclusive)
	 * @return the range of persisted participants
	 */
	@Override
	public List<PersistedParticipant> getPersistedParticipants(
		int start, int end) {

		return persistedParticipantPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of persisted participants.
	 *
	 * @return the number of persisted participants
	 */
	@Override
	public int getPersistedParticipantsCount() {
		return persistedParticipantPersistence.countAll();
	}

	/**
	 * Updates the persisted participant in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param persistedParticipant the persisted participant
	 * @return the persisted participant that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PersistedParticipant updatePersistedParticipant(
		PersistedParticipant persistedParticipant) {

		return persistedParticipantPersistence.update(persistedParticipant);
	}

	/**
	 */
	@Override
	public void addPersistedBookingPersistedParticipant(
		long bookingId, long participantId) {

		persistedBookingPersistence.addPersistedParticipant(
			bookingId, participantId);
	}

	/**
	 */
	@Override
	public void addPersistedBookingPersistedParticipant(
		long bookingId, PersistedParticipant persistedParticipant) {

		persistedBookingPersistence.addPersistedParticipant(
			bookingId, persistedParticipant);
	}

	/**
	 */
	@Override
	public void addPersistedBookingPersistedParticipants(
		long bookingId, long[] participantIds) {

		persistedBookingPersistence.addPersistedParticipants(
			bookingId, participantIds);
	}

	/**
	 */
	@Override
	public void addPersistedBookingPersistedParticipants(
		long bookingId, List<PersistedParticipant> persistedParticipants) {

		persistedBookingPersistence.addPersistedParticipants(
			bookingId, persistedParticipants);
	}

	/**
	 */
	@Override
	public void clearPersistedBookingPersistedParticipants(long bookingId) {
		persistedBookingPersistence.clearPersistedParticipants(bookingId);
	}

	/**
	 */
	@Override
	public void deletePersistedBookingPersistedParticipant(
		long bookingId, long participantId) {

		persistedBookingPersistence.removePersistedParticipant(
			bookingId, participantId);
	}

	/**
	 */
	@Override
	public void deletePersistedBookingPersistedParticipant(
		long bookingId, PersistedParticipant persistedParticipant) {

		persistedBookingPersistence.removePersistedParticipant(
			bookingId, persistedParticipant);
	}

	/**
	 */
	@Override
	public void deletePersistedBookingPersistedParticipants(
		long bookingId, long[] participantIds) {

		persistedBookingPersistence.removePersistedParticipants(
			bookingId, participantIds);
	}

	/**
	 */
	@Override
	public void deletePersistedBookingPersistedParticipants(
		long bookingId, List<PersistedParticipant> persistedParticipants) {

		persistedBookingPersistence.removePersistedParticipants(
			bookingId, persistedParticipants);
	}

	/**
	 * Returns the bookingIds of the persisted bookings associated with the persisted participant.
	 *
	 * @param participantId the participantId of the persisted participant
	 * @return long[] the bookingIds of persisted bookings associated with the persisted participant
	 */
	@Override
	public long[] getPersistedBookingPrimaryKeys(long participantId) {
		return persistedParticipantPersistence.getPersistedBookingPrimaryKeys(
			participantId);
	}

	/**
	 */
	@Override
	public List<PersistedParticipant> getPersistedBookingPersistedParticipants(
		long bookingId) {

		return persistedParticipantPersistence.
			getPersistedBookingPersistedParticipants(bookingId);
	}

	/**
	 */
	@Override
	public List<PersistedParticipant> getPersistedBookingPersistedParticipants(
		long bookingId, int start, int end) {

		return persistedParticipantPersistence.
			getPersistedBookingPersistedParticipants(bookingId, start, end);
	}

	/**
	 */
	@Override
	public List<PersistedParticipant> getPersistedBookingPersistedParticipants(
		long bookingId, int start, int end,
		OrderByComparator<PersistedParticipant> orderByComparator) {

		return persistedParticipantPersistence.
			getPersistedBookingPersistedParticipants(
				bookingId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getPersistedBookingPersistedParticipantsCount(long bookingId) {
		return persistedBookingPersistence.getPersistedParticipantsSize(
			bookingId);
	}

	/**
	 */
	@Override
	public boolean hasPersistedBookingPersistedParticipant(
		long bookingId, long participantId) {

		return persistedBookingPersistence.containsPersistedParticipant(
			bookingId, participantId);
	}

	/**
	 */
	@Override
	public boolean hasPersistedBookingPersistedParticipants(long bookingId) {
		return persistedBookingPersistence.containsPersistedParticipants(
			bookingId);
	}

	/**
	 */
	@Override
	public void setPersistedBookingPersistedParticipants(
		long bookingId, long[] participantIds) {

		persistedBookingPersistence.setPersistedParticipants(
			bookingId, participantIds);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			PersistedParticipantLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		persistedParticipantLocalService =
			(PersistedParticipantLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return PersistedParticipantLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PersistedParticipant.class;
	}

	protected String getModelClassName() {
		return PersistedParticipant.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				persistedParticipantPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Reference
	protected PersistedAmenityPersistence persistedAmenityPersistence;

	@Reference
	protected PersistedBookingPersistence persistedBookingPersistence;

	@Reference
	protected PersistedOfficePersistence persistedOfficePersistence;

	protected PersistedParticipantLocalService persistedParticipantLocalService;

	@Reference
	protected PersistedParticipantPersistence persistedParticipantPersistence;

	@Reference
	protected PersistedPurposePersistence persistedPurposePersistence;

	@Reference
	protected PersistedRoomPersistence persistedRoomPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.PortletPreferencesLocalService
		portletPreferencesLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}