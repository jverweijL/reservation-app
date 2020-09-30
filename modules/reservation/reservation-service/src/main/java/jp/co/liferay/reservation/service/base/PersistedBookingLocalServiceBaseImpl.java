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

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
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

import jp.co.liferay.reservation.model.PersistedBooking;
import jp.co.liferay.reservation.service.PersistedBookingLocalService;
import jp.co.liferay.reservation.service.persistence.PersistedAmenityPersistence;
import jp.co.liferay.reservation.service.persistence.PersistedBookingPersistence;
import jp.co.liferay.reservation.service.persistence.PersistedOfficePersistence;
import jp.co.liferay.reservation.service.persistence.PersistedParticipantPersistence;
import jp.co.liferay.reservation.service.persistence.PersistedPurposePersistence;
import jp.co.liferay.reservation.service.persistence.PersistedRoomPersistence;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the persisted booking local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link jp.co.liferay.reservation.service.impl.PersistedBookingLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see jp.co.liferay.reservation.service.impl.PersistedBookingLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class PersistedBookingLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements PersistedBookingLocalService, AopService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>PersistedBookingLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>jp.co.liferay.reservation.service.PersistedBookingLocalServiceUtil</code>.
	 */

	/**
	 * Adds the persisted booking to the database. Also notifies the appropriate model listeners.
	 *
	 * @param persistedBooking the persisted booking
	 * @return the persisted booking that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PersistedBooking addPersistedBooking(
		PersistedBooking persistedBooking) {

		persistedBooking.setNew(true);

		return persistedBookingPersistence.update(persistedBooking);
	}

	/**
	 * Creates a new persisted booking with the primary key. Does not add the persisted booking to the database.
	 *
	 * @param bookingId the primary key for the new persisted booking
	 * @return the new persisted booking
	 */
	@Override
	@Transactional(enabled = false)
	public PersistedBooking createPersistedBooking(long bookingId) {
		return persistedBookingPersistence.create(bookingId);
	}

	/**
	 * Deletes the persisted booking with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bookingId the primary key of the persisted booking
	 * @return the persisted booking that was removed
	 * @throws PortalException if a persisted booking with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PersistedBooking deletePersistedBooking(long bookingId)
		throws PortalException {

		return persistedBookingPersistence.remove(bookingId);
	}

	/**
	 * Deletes the persisted booking from the database. Also notifies the appropriate model listeners.
	 *
	 * @param persistedBooking the persisted booking
	 * @return the persisted booking that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PersistedBooking deletePersistedBooking(
		PersistedBooking persistedBooking) {

		return persistedBookingPersistence.remove(persistedBooking);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			PersistedBooking.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return persistedBookingPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>jp.co.liferay.reservation.model.impl.PersistedBookingModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return persistedBookingPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>jp.co.liferay.reservation.model.impl.PersistedBookingModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return persistedBookingPersistence.findWithDynamicQuery(
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
		return persistedBookingPersistence.countWithDynamicQuery(dynamicQuery);
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

		return persistedBookingPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public PersistedBooking fetchPersistedBooking(long bookingId) {
		return persistedBookingPersistence.fetchByPrimaryKey(bookingId);
	}

	/**
	 * Returns the persisted booking with the matching UUID and company.
	 *
	 * @param uuid the persisted booking's UUID
	 * @param companyId the primary key of the company
	 * @return the matching persisted booking, or <code>null</code> if a matching persisted booking could not be found
	 */
	@Override
	public PersistedBooking fetchPersistedBookingByUuidAndCompanyId(
		String uuid, long companyId) {

		return persistedBookingPersistence.fetchByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns the persisted booking with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the persisted booking's external reference code
	 * @return the matching persisted booking, or <code>null</code> if a matching persisted booking could not be found
	 */
	@Override
	public PersistedBooking fetchPersistedBookingByReferenceCode(
		long companyId, String externalReferenceCode) {

		return persistedBookingPersistence.fetchByC_ERC(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns the persisted booking with the primary key.
	 *
	 * @param bookingId the primary key of the persisted booking
	 * @return the persisted booking
	 * @throws PortalException if a persisted booking with the primary key could not be found
	 */
	@Override
	public PersistedBooking getPersistedBooking(long bookingId)
		throws PortalException {

		return persistedBookingPersistence.findByPrimaryKey(bookingId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			persistedBookingLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PersistedBooking.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("bookingId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			persistedBookingLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(PersistedBooking.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("bookingId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			persistedBookingLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PersistedBooking.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("bookingId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<PersistedBooking>() {

				@Override
				public void performAction(PersistedBooking persistedBooking)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, persistedBooking);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(PersistedBooking.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return persistedBookingLocalService.deletePersistedBooking(
			(PersistedBooking)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return persistedBookingPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the persisted booking with the matching UUID and company.
	 *
	 * @param uuid the persisted booking's UUID
	 * @param companyId the primary key of the company
	 * @return the matching persisted booking
	 * @throws PortalException if a matching persisted booking could not be found
	 */
	@Override
	public PersistedBooking getPersistedBookingByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return persistedBookingPersistence.findByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns a range of all the persisted bookings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>jp.co.liferay.reservation.model.impl.PersistedBookingModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of persisted bookings
	 * @param end the upper bound of the range of persisted bookings (not inclusive)
	 * @return the range of persisted bookings
	 */
	@Override
	public List<PersistedBooking> getPersistedBookings(int start, int end) {
		return persistedBookingPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of persisted bookings.
	 *
	 * @return the number of persisted bookings
	 */
	@Override
	public int getPersistedBookingsCount() {
		return persistedBookingPersistence.countAll();
	}

	/**
	 * Updates the persisted booking in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param persistedBooking the persisted booking
	 * @return the persisted booking that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PersistedBooking updatePersistedBooking(
		PersistedBooking persistedBooking) {

		return persistedBookingPersistence.update(persistedBooking);
	}

	/**
	 */
	@Override
	public void addPersistedParticipantPersistedBooking(
		long participantId, long bookingId) {

		persistedParticipantPersistence.addPersistedBooking(
			participantId, bookingId);
	}

	/**
	 */
	@Override
	public void addPersistedParticipantPersistedBooking(
		long participantId, PersistedBooking persistedBooking) {

		persistedParticipantPersistence.addPersistedBooking(
			participantId, persistedBooking);
	}

	/**
	 */
	@Override
	public void addPersistedParticipantPersistedBookings(
		long participantId, long[] bookingIds) {

		persistedParticipantPersistence.addPersistedBookings(
			participantId, bookingIds);
	}

	/**
	 */
	@Override
	public void addPersistedParticipantPersistedBookings(
		long participantId, List<PersistedBooking> persistedBookings) {

		persistedParticipantPersistence.addPersistedBookings(
			participantId, persistedBookings);
	}

	/**
	 */
	@Override
	public void clearPersistedParticipantPersistedBookings(long participantId) {
		persistedParticipantPersistence.clearPersistedBookings(participantId);
	}

	/**
	 */
	@Override
	public void deletePersistedParticipantPersistedBooking(
		long participantId, long bookingId) {

		persistedParticipantPersistence.removePersistedBooking(
			participantId, bookingId);
	}

	/**
	 */
	@Override
	public void deletePersistedParticipantPersistedBooking(
		long participantId, PersistedBooking persistedBooking) {

		persistedParticipantPersistence.removePersistedBooking(
			participantId, persistedBooking);
	}

	/**
	 */
	@Override
	public void deletePersistedParticipantPersistedBookings(
		long participantId, long[] bookingIds) {

		persistedParticipantPersistence.removePersistedBookings(
			participantId, bookingIds);
	}

	/**
	 */
	@Override
	public void deletePersistedParticipantPersistedBookings(
		long participantId, List<PersistedBooking> persistedBookings) {

		persistedParticipantPersistence.removePersistedBookings(
			participantId, persistedBookings);
	}

	/**
	 * Returns the participantIds of the persisted participants associated with the persisted booking.
	 *
	 * @param bookingId the bookingId of the persisted booking
	 * @return long[] the participantIds of persisted participants associated with the persisted booking
	 */
	@Override
	public long[] getPersistedParticipantPrimaryKeys(long bookingId) {
		return persistedBookingPersistence.getPersistedParticipantPrimaryKeys(
			bookingId);
	}

	/**
	 */
	@Override
	public List<PersistedBooking> getPersistedParticipantPersistedBookings(
		long participantId) {

		return persistedBookingPersistence.
			getPersistedParticipantPersistedBookings(participantId);
	}

	/**
	 */
	@Override
	public List<PersistedBooking> getPersistedParticipantPersistedBookings(
		long participantId, int start, int end) {

		return persistedBookingPersistence.
			getPersistedParticipantPersistedBookings(participantId, start, end);
	}

	/**
	 */
	@Override
	public List<PersistedBooking> getPersistedParticipantPersistedBookings(
		long participantId, int start, int end,
		OrderByComparator<PersistedBooking> orderByComparator) {

		return persistedBookingPersistence.
			getPersistedParticipantPersistedBookings(
				participantId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getPersistedParticipantPersistedBookingsCount(
		long participantId) {

		return persistedParticipantPersistence.getPersistedBookingsSize(
			participantId);
	}

	/**
	 */
	@Override
	public boolean hasPersistedParticipantPersistedBooking(
		long participantId, long bookingId) {

		return persistedParticipantPersistence.containsPersistedBooking(
			participantId, bookingId);
	}

	/**
	 */
	@Override
	public boolean hasPersistedParticipantPersistedBookings(
		long participantId) {

		return persistedParticipantPersistence.containsPersistedBookings(
			participantId);
	}

	/**
	 */
	@Override
	public void setPersistedParticipantPersistedBookings(
		long participantId, long[] bookingIds) {

		persistedParticipantPersistence.setPersistedBookings(
			participantId, bookingIds);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			PersistedBookingLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		persistedBookingLocalService = (PersistedBookingLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return PersistedBookingLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PersistedBooking.class;
	}

	protected String getModelClassName() {
		return PersistedBooking.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = persistedBookingPersistence.getDataSource();

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

	protected PersistedBookingLocalService persistedBookingLocalService;

	@Reference
	protected PersistedBookingPersistence persistedBookingPersistence;

	@Reference
	protected PersistedOfficePersistence persistedOfficePersistence;

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