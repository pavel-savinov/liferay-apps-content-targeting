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

package com.liferay.content.targeting.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AnonymousUserUserSegmentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AnonymousUserUserSegmentLocalService
 * @generated
 */
public class AnonymousUserUserSegmentLocalServiceWrapper
	implements AnonymousUserUserSegmentLocalService,
		ServiceWrapper<AnonymousUserUserSegmentLocalService> {
	public AnonymousUserUserSegmentLocalServiceWrapper(
		AnonymousUserUserSegmentLocalService anonymousUserUserSegmentLocalService) {
		_anonymousUserUserSegmentLocalService = anonymousUserUserSegmentLocalService;
	}

	/**
	* Adds the anonymous user user segment to the database. Also notifies the appropriate model listeners.
	*
	* @param anonymousUserUserSegment the anonymous user user segment
	* @return the anonymous user user segment that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.content.targeting.model.AnonymousUserUserSegment addAnonymousUserUserSegment(
		com.liferay.content.targeting.model.AnonymousUserUserSegment anonymousUserUserSegment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.addAnonymousUserUserSegment(anonymousUserUserSegment);
	}

	/**
	* Creates a new anonymous user user segment with the primary key. Does not add the anonymous user user segment to the database.
	*
	* @param anonymousUserUserSegmentId the primary key for the new anonymous user user segment
	* @return the new anonymous user user segment
	*/
	@Override
	public com.liferay.content.targeting.model.AnonymousUserUserSegment createAnonymousUserUserSegment(
		long anonymousUserUserSegmentId) {
		return _anonymousUserUserSegmentLocalService.createAnonymousUserUserSegment(anonymousUserUserSegmentId);
	}

	/**
	* Deletes the anonymous user user segment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param anonymousUserUserSegmentId the primary key of the anonymous user user segment
	* @return the anonymous user user segment that was removed
	* @throws PortalException if a anonymous user user segment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.content.targeting.model.AnonymousUserUserSegment deleteAnonymousUserUserSegment(
		long anonymousUserUserSegmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.deleteAnonymousUserUserSegment(anonymousUserUserSegmentId);
	}

	/**
	* Deletes the anonymous user user segment from the database. Also notifies the appropriate model listeners.
	*
	* @param anonymousUserUserSegment the anonymous user user segment
	* @return the anonymous user user segment that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.content.targeting.model.AnonymousUserUserSegment deleteAnonymousUserUserSegment(
		com.liferay.content.targeting.model.AnonymousUserUserSegment anonymousUserUserSegment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.deleteAnonymousUserUserSegment(anonymousUserUserSegment);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _anonymousUserUserSegmentLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.content.targeting.model.AnonymousUserUserSegment fetchAnonymousUserUserSegment(
		long anonymousUserUserSegmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.fetchAnonymousUserUserSegment(anonymousUserUserSegmentId);
	}

	/**
	* Returns the anonymous user user segment with the primary key.
	*
	* @param anonymousUserUserSegmentId the primary key of the anonymous user user segment
	* @return the anonymous user user segment
	* @throws PortalException if a anonymous user user segment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.content.targeting.model.AnonymousUserUserSegment getAnonymousUserUserSegment(
		long anonymousUserUserSegmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getAnonymousUserUserSegment(anonymousUserUserSegmentId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the anonymous user user segments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of anonymous user user segments
	* @param end the upper bound of the range of anonymous user user segments (not inclusive)
	* @return the range of anonymous user user segments
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.content.targeting.model.AnonymousUserUserSegment> getAnonymousUserUserSegments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getAnonymousUserUserSegments(start,
			end);
	}

	/**
	* Returns the number of anonymous user user segments.
	*
	* @return the number of anonymous user user segments
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getAnonymousUserUserSegmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getAnonymousUserUserSegmentsCount();
	}

	/**
	* Updates the anonymous user user segment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param anonymousUserUserSegment the anonymous user user segment
	* @return the anonymous user user segment that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.content.targeting.model.AnonymousUserUserSegment updateAnonymousUserUserSegment(
		com.liferay.content.targeting.model.AnonymousUserUserSegment anonymousUserUserSegment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.updateAnonymousUserUserSegment(anonymousUserUserSegment);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _anonymousUserUserSegmentLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_anonymousUserUserSegmentLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _anonymousUserUserSegmentLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public com.liferay.content.targeting.model.AnonymousUserUserSegment addAnonymousUserUserSegment(
		long anonymousUserId, long userSegmentId, boolean manual,
		boolean active, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.addAnonymousUserUserSegment(anonymousUserId,
			userSegmentId, manual, active, serviceContext);
	}

	@Override
	public void checkAnonymousUserUserSegments()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_anonymousUserUserSegmentLocalService.checkAnonymousUserUserSegments();
	}

	@Override
	public java.util.List<com.liferay.content.targeting.anonymous.users.model.AnonymousUser> getAnonymousUsersByUserSegmentId(
		long userSegmentId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getAnonymousUsersByUserSegmentId(userSegmentId,
			active);
	}

	@Override
	public int getAnonymousUsersByUserSegmentIdCount(long userSegmentId,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getAnonymousUsersByUserSegmentIdCount(userSegmentId,
			active);
	}

	@Override
	public java.util.List<com.liferay.content.targeting.anonymous.users.model.AnonymousUser> getAnonymousUsersByUserSegmentIds(
		long[] userSegmentIds, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getAnonymousUsersByUserSegmentIds(userSegmentIds,
			active);
	}

	@Override
	public int getAnonymousUsersByUserSegmentIdsCount(long[] userSegmentIds,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getAnonymousUsersByUserSegmentIdsCount(userSegmentIds,
			active);
	}

	@Override
	public java.util.List<com.liferay.content.targeting.model.AnonymousUserUserSegment> getAnonymousUserUserSegments(
		long anonymousUserId, long userSegmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getAnonymousUserUserSegments(anonymousUserId,
			userSegmentId);
	}

	@Override
	public java.util.Date getMaxAge()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getMaxAge();
	}

	@Override
	public java.util.List<com.liferay.content.targeting.model.UserSegment> getUserSegmentsByAnonymousUserId(
		long anonymousUserId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getUserSegmentsByAnonymousUserId(anonymousUserId,
			active);
	}

	@Override
	public int getUserSegmentsByAnonymousUserIdCount(long anonymousUserId,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getUserSegmentsByAnonymousUserIdCount(anonymousUserId,
			active);
	}

	@Override
	public java.util.List<com.liferay.content.targeting.model.UserSegment> getUserSegmentsByUserId(
		long userId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getUserSegmentsByUserId(userId,
			active);
	}

	@Override
	public int getUserSegmentsByUserIdCount(long userId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.getUserSegmentsByUserIdCount(userId,
			active);
	}

	@Override
	public com.liferay.content.targeting.model.AnonymousUserUserSegment updateAnonymousUserUserSegment(
		long anonymousUserUserSegmentId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _anonymousUserUserSegmentLocalService.updateAnonymousUserUserSegment(anonymousUserUserSegmentId,
			serviceContext);
	}

	@Override
	public void updateAnonymousUserUserSegments(long companyId,
		java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_anonymousUserUserSegmentLocalService.updateAnonymousUserUserSegments(companyId,
			modifiedDate);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AnonymousUserUserSegmentLocalService getWrappedAnonymousUserUserSegmentLocalService() {
		return _anonymousUserUserSegmentLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAnonymousUserUserSegmentLocalService(
		AnonymousUserUserSegmentLocalService anonymousUserUserSegmentLocalService) {
		_anonymousUserUserSegmentLocalService = anonymousUserUserSegmentLocalService;
	}

	@Override
	public AnonymousUserUserSegmentLocalService getWrappedService() {
		return _anonymousUserUserSegmentLocalService;
	}

	@Override
	public void setWrappedService(
		AnonymousUserUserSegmentLocalService anonymousUserUserSegmentLocalService) {
		_anonymousUserUserSegmentLocalService = anonymousUserUserSegmentLocalService;
	}

	private AnonymousUserUserSegmentLocalService _anonymousUserUserSegmentLocalService;
}