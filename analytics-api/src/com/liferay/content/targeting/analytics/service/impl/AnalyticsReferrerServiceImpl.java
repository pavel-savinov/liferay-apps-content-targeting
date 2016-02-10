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

package com.liferay.content.targeting.analytics.service.impl;

import com.liferay.content.targeting.analytics.model.AnalyticsReferrer;
import com.liferay.content.targeting.analytics.service.base.AnalyticsReferrerServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the analytics referrer remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.content.targeting.analytics.service.AnalyticsReferrerService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.content.targeting.analytics.service.base.AnalyticsReferrerServiceBaseImpl
 * @see com.liferay.content.targeting.analytics.service.AnalyticsReferrerServiceUtil
 */
public class AnalyticsReferrerServiceImpl
	extends AnalyticsReferrerServiceBaseImpl {

	@Override
	public List<AnalyticsReferrer> getAnalyticsReferrers(long analyticsEventId)
		throws SystemException {

		return analyticsReferrerLocalService.getAnalyticsReferrers(
			analyticsEventId);
	}

}