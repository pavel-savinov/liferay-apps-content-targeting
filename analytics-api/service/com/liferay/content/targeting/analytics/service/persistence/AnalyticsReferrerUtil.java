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

package com.liferay.content.targeting.analytics.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.content.targeting.analytics.model.AnalyticsReferrer;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the analytics referrer service. This utility wraps {@link com.liferay.content.targeting.analytics.service.persistence.impl.AnalyticsReferrerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsReferrerPersistence
 * @see com.liferay.content.targeting.analytics.service.persistence.impl.AnalyticsReferrerPersistenceImpl
 * @generated
 */
@ProviderType
public class AnalyticsReferrerUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AnalyticsReferrer analyticsReferrer) {
		getPersistence().clearCache(analyticsReferrer);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AnalyticsReferrer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AnalyticsReferrer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AnalyticsReferrer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AnalyticsReferrer> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AnalyticsReferrer update(AnalyticsReferrer analyticsReferrer) {
		return getPersistence().update(analyticsReferrer);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AnalyticsReferrer update(
		AnalyticsReferrer analyticsReferrer, ServiceContext serviceContext) {
		return getPersistence().update(analyticsReferrer, serviceContext);
	}

	/**
	* Returns all the analytics referrers where analyticsEventId = &#63;.
	*
	* @param analyticsEventId the analytics event ID
	* @return the matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByAnalyticsEventId(
		long analyticsEventId) {
		return getPersistence().findByAnalyticsEventId(analyticsEventId);
	}

	/**
	* Returns a range of all the analytics referrers where analyticsEventId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param analyticsEventId the analytics event ID
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @return the range of matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByAnalyticsEventId(
		long analyticsEventId, int start, int end) {
		return getPersistence()
				   .findByAnalyticsEventId(analyticsEventId, start, end);
	}

	/**
	* Returns an ordered range of all the analytics referrers where analyticsEventId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param analyticsEventId the analytics event ID
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByAnalyticsEventId(
		long analyticsEventId, int start, int end,
		OrderByComparator<AnalyticsReferrer> orderByComparator) {
		return getPersistence()
				   .findByAnalyticsEventId(analyticsEventId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the analytics referrers where analyticsEventId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param analyticsEventId the analytics event ID
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByAnalyticsEventId(
		long analyticsEventId, int start, int end,
		OrderByComparator<AnalyticsReferrer> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAnalyticsEventId(analyticsEventId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first analytics referrer in the ordered set where analyticsEventId = &#63;.
	*
	* @param analyticsEventId the analytics event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching analytics referrer
	* @throws NoSuchAnalyticsReferrerException if a matching analytics referrer could not be found
	*/
	public static AnalyticsReferrer findByAnalyticsEventId_First(
		long analyticsEventId,
		OrderByComparator<AnalyticsReferrer> orderByComparator)
		throws com.liferay.content.targeting.analytics.exception.NoSuchAnalyticsReferrerException {
		return getPersistence()
				   .findByAnalyticsEventId_First(analyticsEventId,
			orderByComparator);
	}

	/**
	* Returns the first analytics referrer in the ordered set where analyticsEventId = &#63;.
	*
	* @param analyticsEventId the analytics event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching analytics referrer, or <code>null</code> if a matching analytics referrer could not be found
	*/
	public static AnalyticsReferrer fetchByAnalyticsEventId_First(
		long analyticsEventId,
		OrderByComparator<AnalyticsReferrer> orderByComparator) {
		return getPersistence()
				   .fetchByAnalyticsEventId_First(analyticsEventId,
			orderByComparator);
	}

	/**
	* Returns the last analytics referrer in the ordered set where analyticsEventId = &#63;.
	*
	* @param analyticsEventId the analytics event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching analytics referrer
	* @throws NoSuchAnalyticsReferrerException if a matching analytics referrer could not be found
	*/
	public static AnalyticsReferrer findByAnalyticsEventId_Last(
		long analyticsEventId,
		OrderByComparator<AnalyticsReferrer> orderByComparator)
		throws com.liferay.content.targeting.analytics.exception.NoSuchAnalyticsReferrerException {
		return getPersistence()
				   .findByAnalyticsEventId_Last(analyticsEventId,
			orderByComparator);
	}

	/**
	* Returns the last analytics referrer in the ordered set where analyticsEventId = &#63;.
	*
	* @param analyticsEventId the analytics event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching analytics referrer, or <code>null</code> if a matching analytics referrer could not be found
	*/
	public static AnalyticsReferrer fetchByAnalyticsEventId_Last(
		long analyticsEventId,
		OrderByComparator<AnalyticsReferrer> orderByComparator) {
		return getPersistence()
				   .fetchByAnalyticsEventId_Last(analyticsEventId,
			orderByComparator);
	}

	/**
	* Returns the analytics referrers before and after the current analytics referrer in the ordered set where analyticsEventId = &#63;.
	*
	* @param analyticsReferrerId the primary key of the current analytics referrer
	* @param analyticsEventId the analytics event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next analytics referrer
	* @throws NoSuchAnalyticsReferrerException if a analytics referrer with the primary key could not be found
	*/
	public static AnalyticsReferrer[] findByAnalyticsEventId_PrevAndNext(
		long analyticsReferrerId, long analyticsEventId,
		OrderByComparator<AnalyticsReferrer> orderByComparator)
		throws com.liferay.content.targeting.analytics.exception.NoSuchAnalyticsReferrerException {
		return getPersistence()
				   .findByAnalyticsEventId_PrevAndNext(analyticsReferrerId,
			analyticsEventId, orderByComparator);
	}

	/**
	* Removes all the analytics referrers where analyticsEventId = &#63; from the database.
	*
	* @param analyticsEventId the analytics event ID
	*/
	public static void removeByAnalyticsEventId(long analyticsEventId) {
		getPersistence().removeByAnalyticsEventId(analyticsEventId);
	}

	/**
	* Returns the number of analytics referrers where analyticsEventId = &#63;.
	*
	* @param analyticsEventId the analytics event ID
	* @return the number of matching analytics referrers
	*/
	public static int countByAnalyticsEventId(long analyticsEventId) {
		return getPersistence().countByAnalyticsEventId(analyticsEventId);
	}

	/**
	* Returns all the analytics referrers where referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @return the matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByR_R(
		java.lang.String referrerClassName, long referrerClassPK) {
		return getPersistence().findByR_R(referrerClassName, referrerClassPK);
	}

	/**
	* Returns a range of all the analytics referrers where referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @return the range of matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByR_R(
		java.lang.String referrerClassName, long referrerClassPK, int start,
		int end) {
		return getPersistence()
				   .findByR_R(referrerClassName, referrerClassPK, start, end);
	}

	/**
	* Returns an ordered range of all the analytics referrers where referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByR_R(
		java.lang.String referrerClassName, long referrerClassPK, int start,
		int end, OrderByComparator<AnalyticsReferrer> orderByComparator) {
		return getPersistence()
				   .findByR_R(referrerClassName, referrerClassPK, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the analytics referrers where referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByR_R(
		java.lang.String referrerClassName, long referrerClassPK, int start,
		int end, OrderByComparator<AnalyticsReferrer> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByR_R(referrerClassName, referrerClassPK, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first analytics referrer in the ordered set where referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching analytics referrer
	* @throws NoSuchAnalyticsReferrerException if a matching analytics referrer could not be found
	*/
	public static AnalyticsReferrer findByR_R_First(
		java.lang.String referrerClassName, long referrerClassPK,
		OrderByComparator<AnalyticsReferrer> orderByComparator)
		throws com.liferay.content.targeting.analytics.exception.NoSuchAnalyticsReferrerException {
		return getPersistence()
				   .findByR_R_First(referrerClassName, referrerClassPK,
			orderByComparator);
	}

	/**
	* Returns the first analytics referrer in the ordered set where referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching analytics referrer, or <code>null</code> if a matching analytics referrer could not be found
	*/
	public static AnalyticsReferrer fetchByR_R_First(
		java.lang.String referrerClassName, long referrerClassPK,
		OrderByComparator<AnalyticsReferrer> orderByComparator) {
		return getPersistence()
				   .fetchByR_R_First(referrerClassName, referrerClassPK,
			orderByComparator);
	}

	/**
	* Returns the last analytics referrer in the ordered set where referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching analytics referrer
	* @throws NoSuchAnalyticsReferrerException if a matching analytics referrer could not be found
	*/
	public static AnalyticsReferrer findByR_R_Last(
		java.lang.String referrerClassName, long referrerClassPK,
		OrderByComparator<AnalyticsReferrer> orderByComparator)
		throws com.liferay.content.targeting.analytics.exception.NoSuchAnalyticsReferrerException {
		return getPersistence()
				   .findByR_R_Last(referrerClassName, referrerClassPK,
			orderByComparator);
	}

	/**
	* Returns the last analytics referrer in the ordered set where referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching analytics referrer, or <code>null</code> if a matching analytics referrer could not be found
	*/
	public static AnalyticsReferrer fetchByR_R_Last(
		java.lang.String referrerClassName, long referrerClassPK,
		OrderByComparator<AnalyticsReferrer> orderByComparator) {
		return getPersistence()
				   .fetchByR_R_Last(referrerClassName, referrerClassPK,
			orderByComparator);
	}

	/**
	* Returns the analytics referrers before and after the current analytics referrer in the ordered set where referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param analyticsReferrerId the primary key of the current analytics referrer
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next analytics referrer
	* @throws NoSuchAnalyticsReferrerException if a analytics referrer with the primary key could not be found
	*/
	public static AnalyticsReferrer[] findByR_R_PrevAndNext(
		long analyticsReferrerId, java.lang.String referrerClassName,
		long referrerClassPK,
		OrderByComparator<AnalyticsReferrer> orderByComparator)
		throws com.liferay.content.targeting.analytics.exception.NoSuchAnalyticsReferrerException {
		return getPersistence()
				   .findByR_R_PrevAndNext(analyticsReferrerId,
			referrerClassName, referrerClassPK, orderByComparator);
	}

	/**
	* Removes all the analytics referrers where referrerClassName = &#63; and referrerClassPK = &#63; from the database.
	*
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	*/
	public static void removeByR_R(java.lang.String referrerClassName,
		long referrerClassPK) {
		getPersistence().removeByR_R(referrerClassName, referrerClassPK);
	}

	/**
	* Returns the number of analytics referrers where referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @return the number of matching analytics referrers
	*/
	public static int countByR_R(java.lang.String referrerClassName,
		long referrerClassPK) {
		return getPersistence().countByR_R(referrerClassName, referrerClassPK);
	}

	/**
	* Returns all the analytics referrers where analyticsEventId = &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param analyticsEventId the analytics event ID
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @return the matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByA_R_R(long analyticsEventId,
		java.lang.String referrerClassName, long referrerClassPK) {
		return getPersistence()
				   .findByA_R_R(analyticsEventId, referrerClassName,
			referrerClassPK);
	}

	/**
	* Returns a range of all the analytics referrers where analyticsEventId = &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param analyticsEventId the analytics event ID
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @return the range of matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByA_R_R(long analyticsEventId,
		java.lang.String referrerClassName, long referrerClassPK, int start,
		int end) {
		return getPersistence()
				   .findByA_R_R(analyticsEventId, referrerClassName,
			referrerClassPK, start, end);
	}

	/**
	* Returns an ordered range of all the analytics referrers where analyticsEventId = &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param analyticsEventId the analytics event ID
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByA_R_R(long analyticsEventId,
		java.lang.String referrerClassName, long referrerClassPK, int start,
		int end, OrderByComparator<AnalyticsReferrer> orderByComparator) {
		return getPersistence()
				   .findByA_R_R(analyticsEventId, referrerClassName,
			referrerClassPK, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the analytics referrers where analyticsEventId = &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param analyticsEventId the analytics event ID
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByA_R_R(long analyticsEventId,
		java.lang.String referrerClassName, long referrerClassPK, int start,
		int end, OrderByComparator<AnalyticsReferrer> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByA_R_R(analyticsEventId, referrerClassName,
			referrerClassPK, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first analytics referrer in the ordered set where analyticsEventId = &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param analyticsEventId the analytics event ID
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching analytics referrer
	* @throws NoSuchAnalyticsReferrerException if a matching analytics referrer could not be found
	*/
	public static AnalyticsReferrer findByA_R_R_First(long analyticsEventId,
		java.lang.String referrerClassName, long referrerClassPK,
		OrderByComparator<AnalyticsReferrer> orderByComparator)
		throws com.liferay.content.targeting.analytics.exception.NoSuchAnalyticsReferrerException {
		return getPersistence()
				   .findByA_R_R_First(analyticsEventId, referrerClassName,
			referrerClassPK, orderByComparator);
	}

	/**
	* Returns the first analytics referrer in the ordered set where analyticsEventId = &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param analyticsEventId the analytics event ID
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching analytics referrer, or <code>null</code> if a matching analytics referrer could not be found
	*/
	public static AnalyticsReferrer fetchByA_R_R_First(long analyticsEventId,
		java.lang.String referrerClassName, long referrerClassPK,
		OrderByComparator<AnalyticsReferrer> orderByComparator) {
		return getPersistence()
				   .fetchByA_R_R_First(analyticsEventId, referrerClassName,
			referrerClassPK, orderByComparator);
	}

	/**
	* Returns the last analytics referrer in the ordered set where analyticsEventId = &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param analyticsEventId the analytics event ID
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching analytics referrer
	* @throws NoSuchAnalyticsReferrerException if a matching analytics referrer could not be found
	*/
	public static AnalyticsReferrer findByA_R_R_Last(long analyticsEventId,
		java.lang.String referrerClassName, long referrerClassPK,
		OrderByComparator<AnalyticsReferrer> orderByComparator)
		throws com.liferay.content.targeting.analytics.exception.NoSuchAnalyticsReferrerException {
		return getPersistence()
				   .findByA_R_R_Last(analyticsEventId, referrerClassName,
			referrerClassPK, orderByComparator);
	}

	/**
	* Returns the last analytics referrer in the ordered set where analyticsEventId = &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param analyticsEventId the analytics event ID
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching analytics referrer, or <code>null</code> if a matching analytics referrer could not be found
	*/
	public static AnalyticsReferrer fetchByA_R_R_Last(long analyticsEventId,
		java.lang.String referrerClassName, long referrerClassPK,
		OrderByComparator<AnalyticsReferrer> orderByComparator) {
		return getPersistence()
				   .fetchByA_R_R_Last(analyticsEventId, referrerClassName,
			referrerClassPK, orderByComparator);
	}

	/**
	* Returns the analytics referrers before and after the current analytics referrer in the ordered set where analyticsEventId = &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param analyticsReferrerId the primary key of the current analytics referrer
	* @param analyticsEventId the analytics event ID
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next analytics referrer
	* @throws NoSuchAnalyticsReferrerException if a analytics referrer with the primary key could not be found
	*/
	public static AnalyticsReferrer[] findByA_R_R_PrevAndNext(
		long analyticsReferrerId, long analyticsEventId,
		java.lang.String referrerClassName, long referrerClassPK,
		OrderByComparator<AnalyticsReferrer> orderByComparator)
		throws com.liferay.content.targeting.analytics.exception.NoSuchAnalyticsReferrerException {
		return getPersistence()
				   .findByA_R_R_PrevAndNext(analyticsReferrerId,
			analyticsEventId, referrerClassName, referrerClassPK,
			orderByComparator);
	}

	/**
	* Returns all the analytics referrers where analyticsEventId = any &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param analyticsEventIds the analytics event IDs
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @return the matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByA_R_R(
		long[] analyticsEventIds, java.lang.String referrerClassName,
		long referrerClassPK) {
		return getPersistence()
				   .findByA_R_R(analyticsEventIds, referrerClassName,
			referrerClassPK);
	}

	/**
	* Returns a range of all the analytics referrers where analyticsEventId = any &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param analyticsEventIds the analytics event IDs
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @return the range of matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByA_R_R(
		long[] analyticsEventIds, java.lang.String referrerClassName,
		long referrerClassPK, int start, int end) {
		return getPersistence()
				   .findByA_R_R(analyticsEventIds, referrerClassName,
			referrerClassPK, start, end);
	}

	/**
	* Returns an ordered range of all the analytics referrers where analyticsEventId = any &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param analyticsEventIds the analytics event IDs
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByA_R_R(
		long[] analyticsEventIds, java.lang.String referrerClassName,
		long referrerClassPK, int start, int end,
		OrderByComparator<AnalyticsReferrer> orderByComparator) {
		return getPersistence()
				   .findByA_R_R(analyticsEventIds, referrerClassName,
			referrerClassPK, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the analytics referrers where analyticsEventId = &#63; and referrerClassName = &#63; and referrerClassPK = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param analyticsEventId the analytics event ID
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching analytics referrers
	*/
	public static List<AnalyticsReferrer> findByA_R_R(
		long[] analyticsEventIds, java.lang.String referrerClassName,
		long referrerClassPK, int start, int end,
		OrderByComparator<AnalyticsReferrer> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByA_R_R(analyticsEventIds, referrerClassName,
			referrerClassPK, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the analytics referrers where analyticsEventId = &#63; and referrerClassName = &#63; and referrerClassPK = &#63; from the database.
	*
	* @param analyticsEventId the analytics event ID
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	*/
	public static void removeByA_R_R(long analyticsEventId,
		java.lang.String referrerClassName, long referrerClassPK) {
		getPersistence()
			.removeByA_R_R(analyticsEventId, referrerClassName, referrerClassPK);
	}

	/**
	* Returns the number of analytics referrers where analyticsEventId = &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param analyticsEventId the analytics event ID
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @return the number of matching analytics referrers
	*/
	public static int countByA_R_R(long analyticsEventId,
		java.lang.String referrerClassName, long referrerClassPK) {
		return getPersistence()
				   .countByA_R_R(analyticsEventId, referrerClassName,
			referrerClassPK);
	}

	/**
	* Returns the number of analytics referrers where analyticsEventId = any &#63; and referrerClassName = &#63; and referrerClassPK = &#63;.
	*
	* @param analyticsEventIds the analytics event IDs
	* @param referrerClassName the referrer class name
	* @param referrerClassPK the referrer class p k
	* @return the number of matching analytics referrers
	*/
	public static int countByA_R_R(long[] analyticsEventIds,
		java.lang.String referrerClassName, long referrerClassPK) {
		return getPersistence()
				   .countByA_R_R(analyticsEventIds, referrerClassName,
			referrerClassPK);
	}

	/**
	* Caches the analytics referrer in the entity cache if it is enabled.
	*
	* @param analyticsReferrer the analytics referrer
	*/
	public static void cacheResult(AnalyticsReferrer analyticsReferrer) {
		getPersistence().cacheResult(analyticsReferrer);
	}

	/**
	* Caches the analytics referrers in the entity cache if it is enabled.
	*
	* @param analyticsReferrers the analytics referrers
	*/
	public static void cacheResult(List<AnalyticsReferrer> analyticsReferrers) {
		getPersistence().cacheResult(analyticsReferrers);
	}

	/**
	* Creates a new analytics referrer with the primary key. Does not add the analytics referrer to the database.
	*
	* @param analyticsReferrerId the primary key for the new analytics referrer
	* @return the new analytics referrer
	*/
	public static AnalyticsReferrer create(long analyticsReferrerId) {
		return getPersistence().create(analyticsReferrerId);
	}

	/**
	* Removes the analytics referrer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param analyticsReferrerId the primary key of the analytics referrer
	* @return the analytics referrer that was removed
	* @throws NoSuchAnalyticsReferrerException if a analytics referrer with the primary key could not be found
	*/
	public static AnalyticsReferrer remove(long analyticsReferrerId)
		throws com.liferay.content.targeting.analytics.exception.NoSuchAnalyticsReferrerException {
		return getPersistence().remove(analyticsReferrerId);
	}

	public static AnalyticsReferrer updateImpl(
		AnalyticsReferrer analyticsReferrer) {
		return getPersistence().updateImpl(analyticsReferrer);
	}

	/**
	* Returns the analytics referrer with the primary key or throws a {@link NoSuchAnalyticsReferrerException} if it could not be found.
	*
	* @param analyticsReferrerId the primary key of the analytics referrer
	* @return the analytics referrer
	* @throws NoSuchAnalyticsReferrerException if a analytics referrer with the primary key could not be found
	*/
	public static AnalyticsReferrer findByPrimaryKey(long analyticsReferrerId)
		throws com.liferay.content.targeting.analytics.exception.NoSuchAnalyticsReferrerException {
		return getPersistence().findByPrimaryKey(analyticsReferrerId);
	}

	/**
	* Returns the analytics referrer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param analyticsReferrerId the primary key of the analytics referrer
	* @return the analytics referrer, or <code>null</code> if a analytics referrer with the primary key could not be found
	*/
	public static AnalyticsReferrer fetchByPrimaryKey(long analyticsReferrerId) {
		return getPersistence().fetchByPrimaryKey(analyticsReferrerId);
	}

	public static java.util.Map<java.io.Serializable, AnalyticsReferrer> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the analytics referrers.
	*
	* @return the analytics referrers
	*/
	public static List<AnalyticsReferrer> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the analytics referrers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @return the range of analytics referrers
	*/
	public static List<AnalyticsReferrer> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the analytics referrers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of analytics referrers
	*/
	public static List<AnalyticsReferrer> findAll(int start, int end,
		OrderByComparator<AnalyticsReferrer> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the analytics referrers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnalyticsReferrerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of analytics referrers
	* @param end the upper bound of the range of analytics referrers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of analytics referrers
	*/
	public static List<AnalyticsReferrer> findAll(int start, int end,
		OrderByComparator<AnalyticsReferrer> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the analytics referrers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of analytics referrers.
	*
	* @return the number of analytics referrers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AnalyticsReferrerPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AnalyticsReferrerPersistence, AnalyticsReferrerPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AnalyticsReferrerPersistence.class);
}