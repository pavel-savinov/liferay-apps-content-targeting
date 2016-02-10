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

package com.liferay.content.targeting.report.campaign.content.service.persistence;

import com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException;
import com.liferay.content.targeting.report.campaign.content.model.CampaignContent;
import com.liferay.content.targeting.report.campaign.content.model.impl.CampaignContentImpl;
import com.liferay.content.targeting.report.campaign.content.model.impl.CampaignContentModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the campaign content service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CampaignContentPersistence
 * @see CampaignContentUtil
 * @generated
 */
public class CampaignContentPersistenceImpl extends BasePersistenceImpl<CampaignContent>
	implements CampaignContentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CampaignContentUtil} to access the campaign content persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CampaignContentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
			CampaignContentModelImpl.FINDER_CACHE_ENABLED,
			CampaignContentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
			CampaignContentModelImpl.FINDER_CACHE_ENABLED,
			CampaignContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
			CampaignContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CAMPAIGNID =
		new FinderPath(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
			CampaignContentModelImpl.FINDER_CACHE_ENABLED,
			CampaignContentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCampaignId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNID =
		new FinderPath(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
			CampaignContentModelImpl.FINDER_CACHE_ENABLED,
			CampaignContentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCampaignId",
			new String[] { Long.class.getName() },
			CampaignContentModelImpl.CAMPAIGNID_COLUMN_BITMASK |
			CampaignContentModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CAMPAIGNID = new FinderPath(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
			CampaignContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCampaignId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the campaign contents where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching campaign contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CampaignContent> findByCampaignId(long campaignId)
		throws SystemException {
		return findByCampaignId(campaignId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the campaign contents where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.report.campaign.content.model.impl.CampaignContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of campaign contents
	 * @param end the upper bound of the range of campaign contents (not inclusive)
	 * @return the range of matching campaign contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CampaignContent> findByCampaignId(long campaignId, int start,
		int end) throws SystemException {
		return findByCampaignId(campaignId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the campaign contents where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.report.campaign.content.model.impl.CampaignContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of campaign contents
	 * @param end the upper bound of the range of campaign contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching campaign contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CampaignContent> findByCampaignId(long campaignId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNID;
			finderArgs = new Object[] { campaignId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CAMPAIGNID;
			finderArgs = new Object[] { campaignId, start, end, orderByComparator };
		}

		List<CampaignContent> list = (List<CampaignContent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CampaignContent campaignContent : list) {
				if ((campaignId != campaignContent.getCampaignId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CAMPAIGNCONTENT_WHERE);

			query.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CampaignContentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignId);

				if (!pagination) {
					list = (List<CampaignContent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<CampaignContent>(list);
				}
				else {
					list = (List<CampaignContent>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first campaign content in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign content
	 * @throws com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException if a matching campaign content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent findByCampaignId_First(long campaignId,
		OrderByComparator orderByComparator)
		throws NoSuchCampaignContentException, SystemException {
		CampaignContent campaignContent = fetchByCampaignId_First(campaignId,
				orderByComparator);

		if (campaignContent != null) {
			return campaignContent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("campaignId=");
		msg.append(campaignId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCampaignContentException(msg.toString());
	}

	/**
	 * Returns the first campaign content in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign content, or <code>null</code> if a matching campaign content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent fetchByCampaignId_First(long campaignId,
		OrderByComparator orderByComparator) throws SystemException {
		List<CampaignContent> list = findByCampaignId(campaignId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last campaign content in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign content
	 * @throws com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException if a matching campaign content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent findByCampaignId_Last(long campaignId,
		OrderByComparator orderByComparator)
		throws NoSuchCampaignContentException, SystemException {
		CampaignContent campaignContent = fetchByCampaignId_Last(campaignId,
				orderByComparator);

		if (campaignContent != null) {
			return campaignContent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("campaignId=");
		msg.append(campaignId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCampaignContentException(msg.toString());
	}

	/**
	 * Returns the last campaign content in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign content, or <code>null</code> if a matching campaign content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent fetchByCampaignId_Last(long campaignId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCampaignId(campaignId);

		if (count == 0) {
			return null;
		}

		List<CampaignContent> list = findByCampaignId(campaignId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the campaign contents before and after the current campaign content in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignContentId the primary key of the current campaign content
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next campaign content
	 * @throws com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException if a campaign content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent[] findByCampaignId_PrevAndNext(
		long campaignContentId, long campaignId,
		OrderByComparator orderByComparator)
		throws NoSuchCampaignContentException, SystemException {
		CampaignContent campaignContent = findByPrimaryKey(campaignContentId);

		Session session = null;

		try {
			session = openSession();

			CampaignContent[] array = new CampaignContentImpl[3];

			array[0] = getByCampaignId_PrevAndNext(session, campaignContent,
					campaignId, orderByComparator, true);

			array[1] = campaignContent;

			array[2] = getByCampaignId_PrevAndNext(session, campaignContent,
					campaignId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CampaignContent getByCampaignId_PrevAndNext(Session session,
		CampaignContent campaignContent, long campaignId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CAMPAIGNCONTENT_WHERE);

		query.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CampaignContentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(campaignId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(campaignContent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CampaignContent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the campaign contents where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCampaignId(long campaignId) throws SystemException {
		for (CampaignContent campaignContent : findByCampaignId(campaignId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(campaignContent);
		}
	}

	/**
	 * Returns the number of campaign contents where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching campaign contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCampaignId(long campaignId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CAMPAIGNID;

		Object[] finderArgs = new Object[] { campaignId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CAMPAIGNCONTENT_WHERE);

			query.append(_FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CAMPAIGNID_CAMPAIGNID_2 = "campaignContent.campaignId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_GTD = new FinderPath(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
			CampaignContentModelImpl.FINDER_CACHE_ENABLED,
			CampaignContentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByC_GtD",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_GTD = new FinderPath(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
			CampaignContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_GtD",
			new String[] { Long.class.getName(), Date.class.getName() });

	/**
	 * Returns all the campaign contents where campaignId = &#63; and modifiedDate &gt; &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param modifiedDate the modified date
	 * @return the matching campaign contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CampaignContent> findByC_GtD(long campaignId, Date modifiedDate)
		throws SystemException {
		return findByC_GtD(campaignId, modifiedDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the campaign contents where campaignId = &#63; and modifiedDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.report.campaign.content.model.impl.CampaignContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of campaign contents
	 * @param end the upper bound of the range of campaign contents (not inclusive)
	 * @return the range of matching campaign contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CampaignContent> findByC_GtD(long campaignId,
		Date modifiedDate, int start, int end) throws SystemException {
		return findByC_GtD(campaignId, modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the campaign contents where campaignId = &#63; and modifiedDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.report.campaign.content.model.impl.CampaignContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of campaign contents
	 * @param end the upper bound of the range of campaign contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching campaign contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CampaignContent> findByC_GtD(long campaignId,
		Date modifiedDate, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_GTD;
		finderArgs = new Object[] {
				campaignId, modifiedDate,
				
				start, end, orderByComparator
			};

		List<CampaignContent> list = (List<CampaignContent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (CampaignContent campaignContent : list) {
				if ((campaignId != campaignContent.getCampaignId()) ||
						(modifiedDate.getTime() >= campaignContent.getModifiedDate()
																	  .getTime())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_CAMPAIGNCONTENT_WHERE);

			query.append(_FINDER_COLUMN_C_GTD_CAMPAIGNID_2);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_C_GTD_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_C_GTD_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CampaignContentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignId);

				if (bindModifiedDate) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				if (!pagination) {
					list = (List<CampaignContent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<CampaignContent>(list);
				}
				else {
					list = (List<CampaignContent>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first campaign content in the ordered set where campaignId = &#63; and modifiedDate &gt; &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign content
	 * @throws com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException if a matching campaign content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent findByC_GtD_First(long campaignId,
		Date modifiedDate, OrderByComparator orderByComparator)
		throws NoSuchCampaignContentException, SystemException {
		CampaignContent campaignContent = fetchByC_GtD_First(campaignId,
				modifiedDate, orderByComparator);

		if (campaignContent != null) {
			return campaignContent;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("campaignId=");
		msg.append(campaignId);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCampaignContentException(msg.toString());
	}

	/**
	 * Returns the first campaign content in the ordered set where campaignId = &#63; and modifiedDate &gt; &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign content, or <code>null</code> if a matching campaign content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent fetchByC_GtD_First(long campaignId,
		Date modifiedDate, OrderByComparator orderByComparator)
		throws SystemException {
		List<CampaignContent> list = findByC_GtD(campaignId, modifiedDate, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last campaign content in the ordered set where campaignId = &#63; and modifiedDate &gt; &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign content
	 * @throws com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException if a matching campaign content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent findByC_GtD_Last(long campaignId, Date modifiedDate,
		OrderByComparator orderByComparator)
		throws NoSuchCampaignContentException, SystemException {
		CampaignContent campaignContent = fetchByC_GtD_Last(campaignId,
				modifiedDate, orderByComparator);

		if (campaignContent != null) {
			return campaignContent;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("campaignId=");
		msg.append(campaignId);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCampaignContentException(msg.toString());
	}

	/**
	 * Returns the last campaign content in the ordered set where campaignId = &#63; and modifiedDate &gt; &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign content, or <code>null</code> if a matching campaign content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent fetchByC_GtD_Last(long campaignId,
		Date modifiedDate, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_GtD(campaignId, modifiedDate);

		if (count == 0) {
			return null;
		}

		List<CampaignContent> list = findByC_GtD(campaignId, modifiedDate,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the campaign contents before and after the current campaign content in the ordered set where campaignId = &#63; and modifiedDate &gt; &#63;.
	 *
	 * @param campaignContentId the primary key of the current campaign content
	 * @param campaignId the campaign ID
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next campaign content
	 * @throws com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException if a campaign content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent[] findByC_GtD_PrevAndNext(long campaignContentId,
		long campaignId, Date modifiedDate, OrderByComparator orderByComparator)
		throws NoSuchCampaignContentException, SystemException {
		CampaignContent campaignContent = findByPrimaryKey(campaignContentId);

		Session session = null;

		try {
			session = openSession();

			CampaignContent[] array = new CampaignContentImpl[3];

			array[0] = getByC_GtD_PrevAndNext(session, campaignContent,
					campaignId, modifiedDate, orderByComparator, true);

			array[1] = campaignContent;

			array[2] = getByC_GtD_PrevAndNext(session, campaignContent,
					campaignId, modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected CampaignContent getByC_GtD_PrevAndNext(Session session,
		CampaignContent campaignContent, long campaignId, Date modifiedDate,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CAMPAIGNCONTENT_WHERE);

		query.append(_FINDER_COLUMN_C_GTD_CAMPAIGNID_2);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_C_GTD_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_C_GTD_MODIFIEDDATE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CampaignContentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(campaignId);

		if (bindModifiedDate) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(campaignContent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<CampaignContent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the campaign contents where campaignId = &#63; and modifiedDate &gt; &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param modifiedDate the modified date
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_GtD(long campaignId, Date modifiedDate)
		throws SystemException {
		for (CampaignContent campaignContent : findByC_GtD(campaignId,
				modifiedDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(campaignContent);
		}
	}

	/**
	 * Returns the number of campaign contents where campaignId = &#63; and modifiedDate &gt; &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param modifiedDate the modified date
	 * @return the number of matching campaign contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_GtD(long campaignId, Date modifiedDate)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_GTD;

		Object[] finderArgs = new Object[] { campaignId, modifiedDate };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CAMPAIGNCONTENT_WHERE);

			query.append(_FINDER_COLUMN_C_GTD_CAMPAIGNID_2);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_C_GTD_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_C_GTD_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignId);

				if (bindModifiedDate) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_GTD_CAMPAIGNID_2 = "campaignContent.campaignId = ? AND ";
	private static final String _FINDER_COLUMN_C_GTD_MODIFIEDDATE_1 = "campaignContent.modifiedDate > NULL";
	private static final String _FINDER_COLUMN_C_GTD_MODIFIEDDATE_2 = "campaignContent.modifiedDate > ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C_C_E = new FinderPath(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
			CampaignContentModelImpl.FINDER_CACHE_ENABLED,
			CampaignContentImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_C_C_E",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName(), String.class.getName()
			},
			CampaignContentModelImpl.CAMPAIGNID_COLUMN_BITMASK |
			CampaignContentModelImpl.CLASSNAME_COLUMN_BITMASK |
			CampaignContentModelImpl.CLASSPK_COLUMN_BITMASK |
			CampaignContentModelImpl.EVENTTYPE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C_C_E = new FinderPath(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
			CampaignContentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_C_E",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Long.class.getName(), String.class.getName()
			});

	/**
	 * Returns the campaign content where campaignId = &#63; and className = &#63; and classPK = &#63; and eventType = &#63; or throws a {@link com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException} if it could not be found.
	 *
	 * @param campaignId the campaign ID
	 * @param className the class name
	 * @param classPK the class p k
	 * @param eventType the event type
	 * @return the matching campaign content
	 * @throws com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException if a matching campaign content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent findByC_C_C_E(long campaignId, String className,
		long classPK, String eventType)
		throws NoSuchCampaignContentException, SystemException {
		CampaignContent campaignContent = fetchByC_C_C_E(campaignId, className,
				classPK, eventType);

		if (campaignContent == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("campaignId=");
			msg.append(campaignId);

			msg.append(", className=");
			msg.append(className);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(", eventType=");
			msg.append(eventType);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCampaignContentException(msg.toString());
		}

		return campaignContent;
	}

	/**
	 * Returns the campaign content where campaignId = &#63; and className = &#63; and classPK = &#63; and eventType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param className the class name
	 * @param classPK the class p k
	 * @param eventType the event type
	 * @return the matching campaign content, or <code>null</code> if a matching campaign content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent fetchByC_C_C_E(long campaignId, String className,
		long classPK, String eventType) throws SystemException {
		return fetchByC_C_C_E(campaignId, className, classPK, eventType, true);
	}

	/**
	 * Returns the campaign content where campaignId = &#63; and className = &#63; and classPK = &#63; and eventType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param campaignId the campaign ID
	 * @param className the class name
	 * @param classPK the class p k
	 * @param eventType the event type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching campaign content, or <code>null</code> if a matching campaign content could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent fetchByC_C_C_E(long campaignId, String className,
		long classPK, String eventType, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				campaignId, className, classPK, eventType
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_C_C_E,
					finderArgs, this);
		}

		if (result instanceof CampaignContent) {
			CampaignContent campaignContent = (CampaignContent)result;

			if ((campaignId != campaignContent.getCampaignId()) ||
					!Validator.equals(className, campaignContent.getClassName()) ||
					(classPK != campaignContent.getClassPK()) ||
					!Validator.equals(eventType, campaignContent.getEventType())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_CAMPAIGNCONTENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_C_E_CAMPAIGNID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_C_C_C_E_CLASSNAME_1);
			}
			else if (className.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_C_C_E_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_C_C_C_E_CLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_C_C_C_E_CLASSPK_2);

			boolean bindEventType = false;

			if (eventType == null) {
				query.append(_FINDER_COLUMN_C_C_C_E_EVENTTYPE_1);
			}
			else if (eventType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_C_C_E_EVENTTYPE_3);
			}
			else {
				bindEventType = true;

				query.append(_FINDER_COLUMN_C_C_C_E_EVENTTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignId);

				if (bindClassName) {
					qPos.add(className);
				}

				qPos.add(classPK);

				if (bindEventType) {
					qPos.add(eventType);
				}

				List<CampaignContent> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_C_E,
						finderArgs, list);
				}
				else {
					CampaignContent campaignContent = list.get(0);

					result = campaignContent;

					cacheResult(campaignContent);

					if ((campaignContent.getCampaignId() != campaignId) ||
							(campaignContent.getClassName() == null) ||
							!campaignContent.getClassName().equals(className) ||
							(campaignContent.getClassPK() != classPK) ||
							(campaignContent.getEventType() == null) ||
							!campaignContent.getEventType().equals(eventType)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_C_E,
							finderArgs, campaignContent);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_C_E,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CampaignContent)result;
		}
	}

	/**
	 * Removes the campaign content where campaignId = &#63; and className = &#63; and classPK = &#63; and eventType = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 * @param className the class name
	 * @param classPK the class p k
	 * @param eventType the event type
	 * @return the campaign content that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent removeByC_C_C_E(long campaignId, String className,
		long classPK, String eventType)
		throws NoSuchCampaignContentException, SystemException {
		CampaignContent campaignContent = findByC_C_C_E(campaignId, className,
				classPK, eventType);

		return remove(campaignContent);
	}

	/**
	 * Returns the number of campaign contents where campaignId = &#63; and className = &#63; and classPK = &#63; and eventType = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param className the class name
	 * @param classPK the class p k
	 * @param eventType the event type
	 * @return the number of matching campaign contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_C_C_E(long campaignId, String className, long classPK,
		String eventType) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C_C_E;

		Object[] finderArgs = new Object[] {
				campaignId, className, classPK, eventType
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_CAMPAIGNCONTENT_WHERE);

			query.append(_FINDER_COLUMN_C_C_C_E_CAMPAIGNID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_C_C_C_E_CLASSNAME_1);
			}
			else if (className.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_C_C_E_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_C_C_C_E_CLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_C_C_C_E_CLASSPK_2);

			boolean bindEventType = false;

			if (eventType == null) {
				query.append(_FINDER_COLUMN_C_C_C_E_EVENTTYPE_1);
			}
			else if (eventType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_C_C_E_EVENTTYPE_3);
			}
			else {
				bindEventType = true;

				query.append(_FINDER_COLUMN_C_C_C_E_EVENTTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(campaignId);

				if (bindClassName) {
					qPos.add(className);
				}

				qPos.add(classPK);

				if (bindEventType) {
					qPos.add(eventType);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_C_C_E_CAMPAIGNID_2 = "campaignContent.campaignId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_C_E_CLASSNAME_1 = "campaignContent.className IS NULL AND ";
	private static final String _FINDER_COLUMN_C_C_C_E_CLASSNAME_2 = "campaignContent.className = ? AND ";
	private static final String _FINDER_COLUMN_C_C_C_E_CLASSNAME_3 = "(campaignContent.className IS NULL OR campaignContent.className = '') AND ";
	private static final String _FINDER_COLUMN_C_C_C_E_CLASSPK_2 = "campaignContent.classPK = ? AND ";
	private static final String _FINDER_COLUMN_C_C_C_E_EVENTTYPE_1 = "campaignContent.eventType IS NULL";
	private static final String _FINDER_COLUMN_C_C_C_E_EVENTTYPE_2 = "campaignContent.eventType = ?";
	private static final String _FINDER_COLUMN_C_C_C_E_EVENTTYPE_3 = "(campaignContent.eventType IS NULL OR campaignContent.eventType = '')";

	public CampaignContentPersistenceImpl() {
		setModelClass(CampaignContent.class);
	}

	/**
	 * Caches the campaign content in the entity cache if it is enabled.
	 *
	 * @param campaignContent the campaign content
	 */
	@Override
	public void cacheResult(CampaignContent campaignContent) {
		EntityCacheUtil.putResult(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
			CampaignContentImpl.class, campaignContent.getPrimaryKey(),
			campaignContent);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_C_E,
			new Object[] {
				campaignContent.getCampaignId(), campaignContent.getClassName(),
				campaignContent.getClassPK(), campaignContent.getEventType()
			}, campaignContent);

		campaignContent.resetOriginalValues();
	}

	/**
	 * Caches the campaign contents in the entity cache if it is enabled.
	 *
	 * @param campaignContents the campaign contents
	 */
	@Override
	public void cacheResult(List<CampaignContent> campaignContents) {
		for (CampaignContent campaignContent : campaignContents) {
			if (EntityCacheUtil.getResult(
						CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
						CampaignContentImpl.class,
						campaignContent.getPrimaryKey()) == null) {
				cacheResult(campaignContent);
			}
			else {
				campaignContent.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all campaign contents.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CampaignContentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CampaignContentImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the campaign content.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CampaignContent campaignContent) {
		EntityCacheUtil.removeResult(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
			CampaignContentImpl.class, campaignContent.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(campaignContent);
	}

	@Override
	public void clearCache(List<CampaignContent> campaignContents) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CampaignContent campaignContent : campaignContents) {
			EntityCacheUtil.removeResult(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
				CampaignContentImpl.class, campaignContent.getPrimaryKey());

			clearUniqueFindersCache(campaignContent);
		}
	}

	protected void cacheUniqueFindersCache(CampaignContent campaignContent) {
		if (campaignContent.isNew()) {
			Object[] args = new Object[] {
					campaignContent.getCampaignId(),
					campaignContent.getClassName(), campaignContent.getClassPK(),
					campaignContent.getEventType()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_C_E, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_C_E, args,
				campaignContent);
		}
		else {
			CampaignContentModelImpl campaignContentModelImpl = (CampaignContentModelImpl)campaignContent;

			if ((campaignContentModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_C_C_E.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						campaignContent.getCampaignId(),
						campaignContent.getClassName(),
						campaignContent.getClassPK(),
						campaignContent.getEventType()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C_C_E, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C_C_E, args,
					campaignContent);
			}
		}
	}

	protected void clearUniqueFindersCache(CampaignContent campaignContent) {
		CampaignContentModelImpl campaignContentModelImpl = (CampaignContentModelImpl)campaignContent;

		Object[] args = new Object[] {
				campaignContent.getCampaignId(), campaignContent.getClassName(),
				campaignContent.getClassPK(), campaignContent.getEventType()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_C_E, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_C_E, args);

		if ((campaignContentModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_C_C_E.getColumnBitmask()) != 0) {
			args = new Object[] {
					campaignContentModelImpl.getOriginalCampaignId(),
					campaignContentModelImpl.getOriginalClassName(),
					campaignContentModelImpl.getOriginalClassPK(),
					campaignContentModelImpl.getOriginalEventType()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C_C_E, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C_C_E, args);
		}
	}

	/**
	 * Creates a new campaign content with the primary key. Does not add the campaign content to the database.
	 *
	 * @param campaignContentId the primary key for the new campaign content
	 * @return the new campaign content
	 */
	@Override
	public CampaignContent create(long campaignContentId) {
		CampaignContent campaignContent = new CampaignContentImpl();

		campaignContent.setNew(true);
		campaignContent.setPrimaryKey(campaignContentId);

		return campaignContent;
	}

	/**
	 * Removes the campaign content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param campaignContentId the primary key of the campaign content
	 * @return the campaign content that was removed
	 * @throws com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException if a campaign content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent remove(long campaignContentId)
		throws NoSuchCampaignContentException, SystemException {
		return remove((Serializable)campaignContentId);
	}

	/**
	 * Removes the campaign content with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the campaign content
	 * @return the campaign content that was removed
	 * @throws com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException if a campaign content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent remove(Serializable primaryKey)
		throws NoSuchCampaignContentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CampaignContent campaignContent = (CampaignContent)session.get(CampaignContentImpl.class,
					primaryKey);

			if (campaignContent == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCampaignContentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(campaignContent);
		}
		catch (NoSuchCampaignContentException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CampaignContent removeImpl(CampaignContent campaignContent)
		throws SystemException {
		campaignContent = toUnwrappedModel(campaignContent);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(campaignContent)) {
				campaignContent = (CampaignContent)session.get(CampaignContentImpl.class,
						campaignContent.getPrimaryKeyObj());
			}

			if (campaignContent != null) {
				session.delete(campaignContent);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (campaignContent != null) {
			clearCache(campaignContent);
		}

		return campaignContent;
	}

	@Override
	public CampaignContent updateImpl(
		com.liferay.content.targeting.report.campaign.content.model.CampaignContent campaignContent)
		throws SystemException {
		campaignContent = toUnwrappedModel(campaignContent);

		boolean isNew = campaignContent.isNew();

		CampaignContentModelImpl campaignContentModelImpl = (CampaignContentModelImpl)campaignContent;

		Session session = null;

		try {
			session = openSession();

			if (campaignContent.isNew()) {
				session.save(campaignContent);

				campaignContent.setNew(false);
			}
			else {
				session.merge(campaignContent);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CampaignContentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((campaignContentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						campaignContentModelImpl.getOriginalCampaignId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CAMPAIGNID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNID,
					args);

				args = new Object[] { campaignContentModelImpl.getCampaignId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CAMPAIGNID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CAMPAIGNID,
					args);
			}
		}

		EntityCacheUtil.putResult(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
			CampaignContentImpl.class, campaignContent.getPrimaryKey(),
			campaignContent);

		clearUniqueFindersCache(campaignContent);
		cacheUniqueFindersCache(campaignContent);

		return campaignContent;
	}

	protected CampaignContent toUnwrappedModel(CampaignContent campaignContent) {
		if (campaignContent instanceof CampaignContentImpl) {
			return campaignContent;
		}

		CampaignContentImpl campaignContentImpl = new CampaignContentImpl();

		campaignContentImpl.setNew(campaignContent.isNew());
		campaignContentImpl.setPrimaryKey(campaignContent.getPrimaryKey());

		campaignContentImpl.setCampaignContentId(campaignContent.getCampaignContentId());
		campaignContentImpl.setCompanyId(campaignContent.getCompanyId());
		campaignContentImpl.setCampaignId(campaignContent.getCampaignId());
		campaignContentImpl.setClassName(campaignContent.getClassName());
		campaignContentImpl.setClassPK(campaignContent.getClassPK());
		campaignContentImpl.setEventType(campaignContent.getEventType());
		campaignContentImpl.setCount(campaignContent.getCount());
		campaignContentImpl.setModifiedDate(campaignContent.getModifiedDate());

		return campaignContentImpl;
	}

	/**
	 * Returns the campaign content with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the campaign content
	 * @return the campaign content
	 * @throws com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException if a campaign content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCampaignContentException, SystemException {
		CampaignContent campaignContent = fetchByPrimaryKey(primaryKey);

		if (campaignContent == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCampaignContentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return campaignContent;
	}

	/**
	 * Returns the campaign content with the primary key or throws a {@link com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException} if it could not be found.
	 *
	 * @param campaignContentId the primary key of the campaign content
	 * @return the campaign content
	 * @throws com.liferay.content.targeting.report.campaign.content.NoSuchCampaignContentException if a campaign content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent findByPrimaryKey(long campaignContentId)
		throws NoSuchCampaignContentException, SystemException {
		return findByPrimaryKey((Serializable)campaignContentId);
	}

	/**
	 * Returns the campaign content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the campaign content
	 * @return the campaign content, or <code>null</code> if a campaign content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		CampaignContent campaignContent = (CampaignContent)EntityCacheUtil.getResult(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
				CampaignContentImpl.class, primaryKey);

		if (campaignContent == _nullCampaignContent) {
			return null;
		}

		if (campaignContent == null) {
			Session session = null;

			try {
				session = openSession();

				campaignContent = (CampaignContent)session.get(CampaignContentImpl.class,
						primaryKey);

				if (campaignContent != null) {
					cacheResult(campaignContent);
				}
				else {
					EntityCacheUtil.putResult(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
						CampaignContentImpl.class, primaryKey,
						_nullCampaignContent);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(CampaignContentModelImpl.ENTITY_CACHE_ENABLED,
					CampaignContentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return campaignContent;
	}

	/**
	 * Returns the campaign content with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param campaignContentId the primary key of the campaign content
	 * @return the campaign content, or <code>null</code> if a campaign content with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CampaignContent fetchByPrimaryKey(long campaignContentId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)campaignContentId);
	}

	/**
	 * Returns all the campaign contents.
	 *
	 * @return the campaign contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CampaignContent> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the campaign contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.report.campaign.content.model.impl.CampaignContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign contents
	 * @param end the upper bound of the range of campaign contents (not inclusive)
	 * @return the range of campaign contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CampaignContent> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the campaign contents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.report.campaign.content.model.impl.CampaignContentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign contents
	 * @param end the upper bound of the range of campaign contents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of campaign contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CampaignContent> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<CampaignContent> list = (List<CampaignContent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CAMPAIGNCONTENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CAMPAIGNCONTENT;

				if (pagination) {
					sql = sql.concat(CampaignContentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CampaignContent>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<CampaignContent>(list);
				}
				else {
					list = (List<CampaignContent>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the campaign contents from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (CampaignContent campaignContent : findAll()) {
			remove(campaignContent);
		}
	}

	/**
	 * Returns the number of campaign contents.
	 *
	 * @return the number of campaign contents
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CAMPAIGNCONTENT);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the campaign content persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.content.targeting.report.campaign.content.model.CampaignContent")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CampaignContent>> listenersList = new ArrayList<ModelListener<CampaignContent>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<CampaignContent>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(CampaignContentImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CAMPAIGNCONTENT = "SELECT campaignContent FROM CampaignContent campaignContent";
	private static final String _SQL_SELECT_CAMPAIGNCONTENT_WHERE = "SELECT campaignContent FROM CampaignContent campaignContent WHERE ";
	private static final String _SQL_COUNT_CAMPAIGNCONTENT = "SELECT COUNT(campaignContent) FROM CampaignContent campaignContent";
	private static final String _SQL_COUNT_CAMPAIGNCONTENT_WHERE = "SELECT COUNT(campaignContent) FROM CampaignContent campaignContent WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "campaignContent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CampaignContent exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CampaignContent exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CampaignContentPersistenceImpl.class);
	private static CampaignContent _nullCampaignContent = new CampaignContentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CampaignContent> toCacheModel() {
				return _nullCampaignContentCacheModel;
			}
		};

	private static CacheModel<CampaignContent> _nullCampaignContentCacheModel = new CacheModel<CampaignContent>() {
			@Override
			public CampaignContent toEntityModel() {
				return _nullCampaignContent;
			}
		};
}