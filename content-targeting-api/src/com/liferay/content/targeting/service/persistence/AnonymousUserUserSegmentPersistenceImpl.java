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

package com.liferay.content.targeting.service.persistence;

import com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException;
import com.liferay.content.targeting.model.AnonymousUserUserSegment;
import com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentImpl;
import com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl;

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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the anonymous user user segment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnonymousUserUserSegmentPersistence
 * @see AnonymousUserUserSegmentUtil
 * @generated
 */
public class AnonymousUserUserSegmentPersistenceImpl extends BasePersistenceImpl<AnonymousUserUserSegment>
	implements AnonymousUserUserSegmentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AnonymousUserUserSegmentUtil} to access the anonymous user user segment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AnonymousUserUserSegmentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED,
			AnonymousUserUserSegmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED,
			AnonymousUserUserSegmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ANONYMOUSUSERID =
		new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED,
			AnonymousUserUserSegmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAnonymousUserId",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANONYMOUSUSERID =
		new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED,
			AnonymousUserUserSegmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAnonymousUserId",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			AnonymousUserUserSegmentModelImpl.ANONYMOUSUSERID_COLUMN_BITMASK |
			AnonymousUserUserSegmentModelImpl.ACTIVE_COLUMN_BITMASK |
			AnonymousUserUserSegmentModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ANONYMOUSUSERID = new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAnonymousUserId",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the anonymous user user segments where anonymousUserId = &#63; and active = &#63;.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param active the active
	 * @return the matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByAnonymousUserId(
		long anonymousUserId, boolean active) throws SystemException {
		return findByAnonymousUserId(anonymousUserId, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the anonymous user user segments where anonymousUserId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param active the active
	 * @param start the lower bound of the range of anonymous user user segments
	 * @param end the upper bound of the range of anonymous user user segments (not inclusive)
	 * @return the range of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByAnonymousUserId(
		long anonymousUserId, boolean active, int start, int end)
		throws SystemException {
		return findByAnonymousUserId(anonymousUserId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the anonymous user user segments where anonymousUserId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param active the active
	 * @param start the lower bound of the range of anonymous user user segments
	 * @param end the upper bound of the range of anonymous user user segments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByAnonymousUserId(
		long anonymousUserId, boolean active, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANONYMOUSUSERID;
			finderArgs = new Object[] { anonymousUserId, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ANONYMOUSUSERID;
			finderArgs = new Object[] {
					anonymousUserId, active,
					
					start, end, orderByComparator
				};
		}

		List<AnonymousUserUserSegment> list = (List<AnonymousUserUserSegment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AnonymousUserUserSegment anonymousUserUserSegment : list) {
				if ((anonymousUserId != anonymousUserUserSegment.getAnonymousUserId()) ||
						(active != anonymousUserUserSegment.getActive())) {
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

			query.append(_SQL_SELECT_ANONYMOUSUSERUSERSEGMENT_WHERE);

			query.append(_FINDER_COLUMN_ANONYMOUSUSERID_ANONYMOUSUSERID_2);

			query.append(_FINDER_COLUMN_ANONYMOUSUSERID_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AnonymousUserUserSegmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(anonymousUserId);

				qPos.add(active);

				if (!pagination) {
					list = (List<AnonymousUserUserSegment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AnonymousUserUserSegment>(list);
				}
				else {
					list = (List<AnonymousUserUserSegment>)QueryUtil.list(q,
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
	 * Returns the first anonymous user user segment in the ordered set where anonymousUserId = &#63; and active = &#63;.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment findByAnonymousUserId_First(
		long anonymousUserId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = fetchByAnonymousUserId_First(anonymousUserId,
				active, orderByComparator);

		if (anonymousUserUserSegment != null) {
			return anonymousUserUserSegment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("anonymousUserId=");
		msg.append(anonymousUserId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymousUserUserSegmentException(msg.toString());
	}

	/**
	 * Returns the first anonymous user user segment in the ordered set where anonymousUserId = &#63; and active = &#63;.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymous user user segment, or <code>null</code> if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment fetchByAnonymousUserId_First(
		long anonymousUserId, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		List<AnonymousUserUserSegment> list = findByAnonymousUserId(anonymousUserId,
				active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last anonymous user user segment in the ordered set where anonymousUserId = &#63; and active = &#63;.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment findByAnonymousUserId_Last(
		long anonymousUserId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = fetchByAnonymousUserId_Last(anonymousUserId,
				active, orderByComparator);

		if (anonymousUserUserSegment != null) {
			return anonymousUserUserSegment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("anonymousUserId=");
		msg.append(anonymousUserId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymousUserUserSegmentException(msg.toString());
	}

	/**
	 * Returns the last anonymous user user segment in the ordered set where anonymousUserId = &#63; and active = &#63;.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymous user user segment, or <code>null</code> if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment fetchByAnonymousUserId_Last(
		long anonymousUserId, boolean active,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByAnonymousUserId(anonymousUserId, active);

		if (count == 0) {
			return null;
		}

		List<AnonymousUserUserSegment> list = findByAnonymousUserId(anonymousUserId,
				active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the anonymous user user segments before and after the current anonymous user user segment in the ordered set where anonymousUserId = &#63; and active = &#63;.
	 *
	 * @param anonymousUserUserSegmentId the primary key of the current anonymous user user segment
	 * @param anonymousUserId the anonymous user ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a anonymous user user segment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment[] findByAnonymousUserId_PrevAndNext(
		long anonymousUserUserSegmentId, long anonymousUserId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = findByPrimaryKey(anonymousUserUserSegmentId);

		Session session = null;

		try {
			session = openSession();

			AnonymousUserUserSegment[] array = new AnonymousUserUserSegmentImpl[3];

			array[0] = getByAnonymousUserId_PrevAndNext(session,
					anonymousUserUserSegment, anonymousUserId, active,
					orderByComparator, true);

			array[1] = anonymousUserUserSegment;

			array[2] = getByAnonymousUserId_PrevAndNext(session,
					anonymousUserUserSegment, anonymousUserId, active,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AnonymousUserUserSegment getByAnonymousUserId_PrevAndNext(
		Session session, AnonymousUserUserSegment anonymousUserUserSegment,
		long anonymousUserId, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ANONYMOUSUSERUSERSEGMENT_WHERE);

		query.append(_FINDER_COLUMN_ANONYMOUSUSERID_ANONYMOUSUSERID_2);

		query.append(_FINDER_COLUMN_ANONYMOUSUSERID_ACTIVE_2);

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
			query.append(AnonymousUserUserSegmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(anonymousUserId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(anonymousUserUserSegment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AnonymousUserUserSegment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the anonymous user user segments where anonymousUserId = &#63; and active = &#63; from the database.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByAnonymousUserId(long anonymousUserId, boolean active)
		throws SystemException {
		for (AnonymousUserUserSegment anonymousUserUserSegment : findByAnonymousUserId(
				anonymousUserId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(anonymousUserUserSegment);
		}
	}

	/**
	 * Returns the number of anonymous user user segments where anonymousUserId = &#63; and active = &#63;.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param active the active
	 * @return the number of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByAnonymousUserId(long anonymousUserId, boolean active)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ANONYMOUSUSERID;

		Object[] finderArgs = new Object[] { anonymousUserId, active };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ANONYMOUSUSERUSERSEGMENT_WHERE);

			query.append(_FINDER_COLUMN_ANONYMOUSUSERID_ANONYMOUSUSERID_2);

			query.append(_FINDER_COLUMN_ANONYMOUSUSERID_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(anonymousUserId);

				qPos.add(active);

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

	private static final String _FINDER_COLUMN_ANONYMOUSUSERID_ANONYMOUSUSERID_2 =
		"anonymousUserUserSegment.anonymousUserId = ? AND ";
	private static final String _FINDER_COLUMN_ANONYMOUSUSERID_ACTIVE_2 = "anonymousUserUserSegment.active = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_A_U = new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED,
			AnonymousUserUserSegmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByA_U",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_U = new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED,
			AnonymousUserUserSegmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByA_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			AnonymousUserUserSegmentModelImpl.ANONYMOUSUSERID_COLUMN_BITMASK |
			AnonymousUserUserSegmentModelImpl.USERSEGMENTID_COLUMN_BITMASK |
			AnonymousUserUserSegmentModelImpl.MODIFIEDDATE_COLUMN_BITMASK |
			AnonymousUserUserSegmentModelImpl.ACTIVE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_A_U = new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByA_U",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the anonymous user user segments where anonymousUserId = &#63; and userSegmentId = &#63;.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param userSegmentId the user segment ID
	 * @return the matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByA_U(long anonymousUserId,
		long userSegmentId) throws SystemException {
		return findByA_U(anonymousUserId, userSegmentId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the anonymous user user segments where anonymousUserId = &#63; and userSegmentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param userSegmentId the user segment ID
	 * @param start the lower bound of the range of anonymous user user segments
	 * @param end the upper bound of the range of anonymous user user segments (not inclusive)
	 * @return the range of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByA_U(long anonymousUserId,
		long userSegmentId, int start, int end) throws SystemException {
		return findByA_U(anonymousUserId, userSegmentId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the anonymous user user segments where anonymousUserId = &#63; and userSegmentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param userSegmentId the user segment ID
	 * @param start the lower bound of the range of anonymous user user segments
	 * @param end the upper bound of the range of anonymous user user segments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByA_U(long anonymousUserId,
		long userSegmentId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_U;
			finderArgs = new Object[] { anonymousUserId, userSegmentId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_A_U;
			finderArgs = new Object[] {
					anonymousUserId, userSegmentId,
					
					start, end, orderByComparator
				};
		}

		List<AnonymousUserUserSegment> list = (List<AnonymousUserUserSegment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AnonymousUserUserSegment anonymousUserUserSegment : list) {
				if ((anonymousUserId != anonymousUserUserSegment.getAnonymousUserId()) ||
						(userSegmentId != anonymousUserUserSegment.getUserSegmentId())) {
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

			query.append(_SQL_SELECT_ANONYMOUSUSERUSERSEGMENT_WHERE);

			query.append(_FINDER_COLUMN_A_U_ANONYMOUSUSERID_2);

			query.append(_FINDER_COLUMN_A_U_USERSEGMENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AnonymousUserUserSegmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(anonymousUserId);

				qPos.add(userSegmentId);

				if (!pagination) {
					list = (List<AnonymousUserUserSegment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AnonymousUserUserSegment>(list);
				}
				else {
					list = (List<AnonymousUserUserSegment>)QueryUtil.list(q,
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
	 * Returns the first anonymous user user segment in the ordered set where anonymousUserId = &#63; and userSegmentId = &#63;.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param userSegmentId the user segment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment findByA_U_First(long anonymousUserId,
		long userSegmentId, OrderByComparator orderByComparator)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = fetchByA_U_First(anonymousUserId,
				userSegmentId, orderByComparator);

		if (anonymousUserUserSegment != null) {
			return anonymousUserUserSegment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("anonymousUserId=");
		msg.append(anonymousUserId);

		msg.append(", userSegmentId=");
		msg.append(userSegmentId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymousUserUserSegmentException(msg.toString());
	}

	/**
	 * Returns the first anonymous user user segment in the ordered set where anonymousUserId = &#63; and userSegmentId = &#63;.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param userSegmentId the user segment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymous user user segment, or <code>null</code> if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment fetchByA_U_First(long anonymousUserId,
		long userSegmentId, OrderByComparator orderByComparator)
		throws SystemException {
		List<AnonymousUserUserSegment> list = findByA_U(anonymousUserId,
				userSegmentId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last anonymous user user segment in the ordered set where anonymousUserId = &#63; and userSegmentId = &#63;.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param userSegmentId the user segment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment findByA_U_Last(long anonymousUserId,
		long userSegmentId, OrderByComparator orderByComparator)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = fetchByA_U_Last(anonymousUserId,
				userSegmentId, orderByComparator);

		if (anonymousUserUserSegment != null) {
			return anonymousUserUserSegment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("anonymousUserId=");
		msg.append(anonymousUserId);

		msg.append(", userSegmentId=");
		msg.append(userSegmentId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymousUserUserSegmentException(msg.toString());
	}

	/**
	 * Returns the last anonymous user user segment in the ordered set where anonymousUserId = &#63; and userSegmentId = &#63;.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param userSegmentId the user segment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymous user user segment, or <code>null</code> if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment fetchByA_U_Last(long anonymousUserId,
		long userSegmentId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByA_U(anonymousUserId, userSegmentId);

		if (count == 0) {
			return null;
		}

		List<AnonymousUserUserSegment> list = findByA_U(anonymousUserId,
				userSegmentId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the anonymous user user segments before and after the current anonymous user user segment in the ordered set where anonymousUserId = &#63; and userSegmentId = &#63;.
	 *
	 * @param anonymousUserUserSegmentId the primary key of the current anonymous user user segment
	 * @param anonymousUserId the anonymous user ID
	 * @param userSegmentId the user segment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a anonymous user user segment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment[] findByA_U_PrevAndNext(
		long anonymousUserUserSegmentId, long anonymousUserId,
		long userSegmentId, OrderByComparator orderByComparator)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = findByPrimaryKey(anonymousUserUserSegmentId);

		Session session = null;

		try {
			session = openSession();

			AnonymousUserUserSegment[] array = new AnonymousUserUserSegmentImpl[3];

			array[0] = getByA_U_PrevAndNext(session, anonymousUserUserSegment,
					anonymousUserId, userSegmentId, orderByComparator, true);

			array[1] = anonymousUserUserSegment;

			array[2] = getByA_U_PrevAndNext(session, anonymousUserUserSegment,
					anonymousUserId, userSegmentId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AnonymousUserUserSegment getByA_U_PrevAndNext(Session session,
		AnonymousUserUserSegment anonymousUserUserSegment,
		long anonymousUserId, long userSegmentId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ANONYMOUSUSERUSERSEGMENT_WHERE);

		query.append(_FINDER_COLUMN_A_U_ANONYMOUSUSERID_2);

		query.append(_FINDER_COLUMN_A_U_USERSEGMENTID_2);

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
			query.append(AnonymousUserUserSegmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(anonymousUserId);

		qPos.add(userSegmentId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(anonymousUserUserSegment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AnonymousUserUserSegment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the anonymous user user segments where anonymousUserId = &#63; and userSegmentId = &#63; from the database.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param userSegmentId the user segment ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByA_U(long anonymousUserId, long userSegmentId)
		throws SystemException {
		for (AnonymousUserUserSegment anonymousUserUserSegment : findByA_U(
				anonymousUserId, userSegmentId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(anonymousUserUserSegment);
		}
	}

	/**
	 * Returns the number of anonymous user user segments where anonymousUserId = &#63; and userSegmentId = &#63;.
	 *
	 * @param anonymousUserId the anonymous user ID
	 * @param userSegmentId the user segment ID
	 * @return the number of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByA_U(long anonymousUserId, long userSegmentId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_A_U;

		Object[] finderArgs = new Object[] { anonymousUserId, userSegmentId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ANONYMOUSUSERUSERSEGMENT_WHERE);

			query.append(_FINDER_COLUMN_A_U_ANONYMOUSUSERID_2);

			query.append(_FINDER_COLUMN_A_U_USERSEGMENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(anonymousUserId);

				qPos.add(userSegmentId);

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

	private static final String _FINDER_COLUMN_A_U_ANONYMOUSUSERID_2 = "anonymousUserUserSegment.anonymousUserId = ? AND ";
	private static final String _FINDER_COLUMN_A_U_USERSEGMENTID_2 = "anonymousUserUserSegment.userSegmentId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_LTD_M = new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED,
			AnonymousUserUserSegmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_LtD_M",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_LTD_M = new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_LtD_M",
			new String[] {
				Long.class.getName(), Date.class.getName(),
				Boolean.class.getName()
			});

	/**
	 * Returns all the anonymous user user segments where companyId = &#63; and modifiedDate &lt; &#63; and manual = &#63;.
	 *
	 * @param companyId the company ID
	 * @param modifiedDate the modified date
	 * @param manual the manual
	 * @return the matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByC_LtD_M(long companyId,
		Date modifiedDate, boolean manual) throws SystemException {
		return findByC_LtD_M(companyId, modifiedDate, manual,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the anonymous user user segments where companyId = &#63; and modifiedDate &lt; &#63; and manual = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param modifiedDate the modified date
	 * @param manual the manual
	 * @param start the lower bound of the range of anonymous user user segments
	 * @param end the upper bound of the range of anonymous user user segments (not inclusive)
	 * @return the range of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByC_LtD_M(long companyId,
		Date modifiedDate, boolean manual, int start, int end)
		throws SystemException {
		return findByC_LtD_M(companyId, modifiedDate, manual, start, end, null);
	}

	/**
	 * Returns an ordered range of all the anonymous user user segments where companyId = &#63; and modifiedDate &lt; &#63; and manual = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param modifiedDate the modified date
	 * @param manual the manual
	 * @param start the lower bound of the range of anonymous user user segments
	 * @param end the upper bound of the range of anonymous user user segments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByC_LtD_M(long companyId,
		Date modifiedDate, boolean manual, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_LTD_M;
		finderArgs = new Object[] {
				companyId, modifiedDate, manual,
				
				start, end, orderByComparator
			};

		List<AnonymousUserUserSegment> list = (List<AnonymousUserUserSegment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AnonymousUserUserSegment anonymousUserUserSegment : list) {
				if ((companyId != anonymousUserUserSegment.getCompanyId()) ||
						(modifiedDate.getTime() <= anonymousUserUserSegment.getModifiedDate()
																			   .getTime()) ||
						(manual != anonymousUserUserSegment.getManual())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ANONYMOUSUSERUSERSEGMENT_WHERE);

			query.append(_FINDER_COLUMN_C_LTD_M_COMPANYID_2);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_C_LTD_M_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_C_LTD_M_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_C_LTD_M_MANUAL_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AnonymousUserUserSegmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindModifiedDate) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(manual);

				if (!pagination) {
					list = (List<AnonymousUserUserSegment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AnonymousUserUserSegment>(list);
				}
				else {
					list = (List<AnonymousUserUserSegment>)QueryUtil.list(q,
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
	 * Returns the first anonymous user user segment in the ordered set where companyId = &#63; and modifiedDate &lt; &#63; and manual = &#63;.
	 *
	 * @param companyId the company ID
	 * @param modifiedDate the modified date
	 * @param manual the manual
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment findByC_LtD_M_First(long companyId,
		Date modifiedDate, boolean manual, OrderByComparator orderByComparator)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = fetchByC_LtD_M_First(companyId,
				modifiedDate, manual, orderByComparator);

		if (anonymousUserUserSegment != null) {
			return anonymousUserUserSegment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", manual=");
		msg.append(manual);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymousUserUserSegmentException(msg.toString());
	}

	/**
	 * Returns the first anonymous user user segment in the ordered set where companyId = &#63; and modifiedDate &lt; &#63; and manual = &#63;.
	 *
	 * @param companyId the company ID
	 * @param modifiedDate the modified date
	 * @param manual the manual
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymous user user segment, or <code>null</code> if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment fetchByC_LtD_M_First(long companyId,
		Date modifiedDate, boolean manual, OrderByComparator orderByComparator)
		throws SystemException {
		List<AnonymousUserUserSegment> list = findByC_LtD_M(companyId,
				modifiedDate, manual, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last anonymous user user segment in the ordered set where companyId = &#63; and modifiedDate &lt; &#63; and manual = &#63;.
	 *
	 * @param companyId the company ID
	 * @param modifiedDate the modified date
	 * @param manual the manual
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment findByC_LtD_M_Last(long companyId,
		Date modifiedDate, boolean manual, OrderByComparator orderByComparator)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = fetchByC_LtD_M_Last(companyId,
				modifiedDate, manual, orderByComparator);

		if (anonymousUserUserSegment != null) {
			return anonymousUserUserSegment;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", modifiedDate=");
		msg.append(modifiedDate);

		msg.append(", manual=");
		msg.append(manual);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymousUserUserSegmentException(msg.toString());
	}

	/**
	 * Returns the last anonymous user user segment in the ordered set where companyId = &#63; and modifiedDate &lt; &#63; and manual = &#63;.
	 *
	 * @param companyId the company ID
	 * @param modifiedDate the modified date
	 * @param manual the manual
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymous user user segment, or <code>null</code> if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment fetchByC_LtD_M_Last(long companyId,
		Date modifiedDate, boolean manual, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_LtD_M(companyId, modifiedDate, manual);

		if (count == 0) {
			return null;
		}

		List<AnonymousUserUserSegment> list = findByC_LtD_M(companyId,
				modifiedDate, manual, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the anonymous user user segments before and after the current anonymous user user segment in the ordered set where companyId = &#63; and modifiedDate &lt; &#63; and manual = &#63;.
	 *
	 * @param anonymousUserUserSegmentId the primary key of the current anonymous user user segment
	 * @param companyId the company ID
	 * @param modifiedDate the modified date
	 * @param manual the manual
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a anonymous user user segment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment[] findByC_LtD_M_PrevAndNext(
		long anonymousUserUserSegmentId, long companyId, Date modifiedDate,
		boolean manual, OrderByComparator orderByComparator)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = findByPrimaryKey(anonymousUserUserSegmentId);

		Session session = null;

		try {
			session = openSession();

			AnonymousUserUserSegment[] array = new AnonymousUserUserSegmentImpl[3];

			array[0] = getByC_LtD_M_PrevAndNext(session,
					anonymousUserUserSegment, companyId, modifiedDate, manual,
					orderByComparator, true);

			array[1] = anonymousUserUserSegment;

			array[2] = getByC_LtD_M_PrevAndNext(session,
					anonymousUserUserSegment, companyId, modifiedDate, manual,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AnonymousUserUserSegment getByC_LtD_M_PrevAndNext(
		Session session, AnonymousUserUserSegment anonymousUserUserSegment,
		long companyId, Date modifiedDate, boolean manual,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ANONYMOUSUSERUSERSEGMENT_WHERE);

		query.append(_FINDER_COLUMN_C_LTD_M_COMPANYID_2);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_C_LTD_M_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_C_LTD_M_MODIFIEDDATE_2);
		}

		query.append(_FINDER_COLUMN_C_LTD_M_MANUAL_2);

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
			query.append(AnonymousUserUserSegmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindModifiedDate) {
			qPos.add(CalendarUtil.getTimestamp(modifiedDate));
		}

		qPos.add(manual);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(anonymousUserUserSegment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AnonymousUserUserSegment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the anonymous user user segments where companyId = &#63; and modifiedDate &lt; &#63; and manual = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param modifiedDate the modified date
	 * @param manual the manual
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_LtD_M(long companyId, Date modifiedDate,
		boolean manual) throws SystemException {
		for (AnonymousUserUserSegment anonymousUserUserSegment : findByC_LtD_M(
				companyId, modifiedDate, manual, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(anonymousUserUserSegment);
		}
	}

	/**
	 * Returns the number of anonymous user user segments where companyId = &#63; and modifiedDate &lt; &#63; and manual = &#63;.
	 *
	 * @param companyId the company ID
	 * @param modifiedDate the modified date
	 * @param manual the manual
	 * @return the number of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_LtD_M(long companyId, Date modifiedDate, boolean manual)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_LTD_M;

		Object[] finderArgs = new Object[] { companyId, modifiedDate, manual };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ANONYMOUSUSERUSERSEGMENT_WHERE);

			query.append(_FINDER_COLUMN_C_LTD_M_COMPANYID_2);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_C_LTD_M_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_C_LTD_M_MODIFIEDDATE_2);
			}

			query.append(_FINDER_COLUMN_C_LTD_M_MANUAL_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindModifiedDate) {
					qPos.add(CalendarUtil.getTimestamp(modifiedDate));
				}

				qPos.add(manual);

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

	private static final String _FINDER_COLUMN_C_LTD_M_COMPANYID_2 = "anonymousUserUserSegment.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_LTD_M_MODIFIEDDATE_1 = "anonymousUserUserSegment.modifiedDate < NULL AND ";
	private static final String _FINDER_COLUMN_C_LTD_M_MODIFIEDDATE_2 = "anonymousUserUserSegment.modifiedDate < ? AND ";
	private static final String _FINDER_COLUMN_C_LTD_M_MANUAL_2 = "anonymousUserUserSegment.manual = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERSEGMENTIDS =
		new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED,
			AnonymousUserUserSegmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserSegmentIds",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERSEGMENTIDS =
		new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED,
			AnonymousUserUserSegmentImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserSegmentIds",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			AnonymousUserUserSegmentModelImpl.USERSEGMENTID_COLUMN_BITMASK |
			AnonymousUserUserSegmentModelImpl.ACTIVE_COLUMN_BITMASK |
			AnonymousUserUserSegmentModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERSEGMENTIDS = new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserSegmentIds",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_USERSEGMENTIDS =
		new FinderPath(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByUserSegmentIds",
			new String[] { Long.class.getName(), Boolean.class.getName() });

	/**
	 * Returns all the anonymous user user segments where userSegmentId = &#63; and active = &#63;.
	 *
	 * @param userSegmentId the user segment ID
	 * @param active the active
	 * @return the matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByUserSegmentIds(
		long userSegmentId, boolean active) throws SystemException {
		return findByUserSegmentIds(userSegmentId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the anonymous user user segments where userSegmentId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userSegmentId the user segment ID
	 * @param active the active
	 * @param start the lower bound of the range of anonymous user user segments
	 * @param end the upper bound of the range of anonymous user user segments (not inclusive)
	 * @return the range of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByUserSegmentIds(
		long userSegmentId, boolean active, int start, int end)
		throws SystemException {
		return findByUserSegmentIds(userSegmentId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the anonymous user user segments where userSegmentId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userSegmentId the user segment ID
	 * @param active the active
	 * @param start the lower bound of the range of anonymous user user segments
	 * @param end the upper bound of the range of anonymous user user segments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByUserSegmentIds(
		long userSegmentId, boolean active, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERSEGMENTIDS;
			finderArgs = new Object[] { userSegmentId, active };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERSEGMENTIDS;
			finderArgs = new Object[] {
					userSegmentId, active,
					
					start, end, orderByComparator
				};
		}

		List<AnonymousUserUserSegment> list = (List<AnonymousUserUserSegment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AnonymousUserUserSegment anonymousUserUserSegment : list) {
				if ((userSegmentId != anonymousUserUserSegment.getUserSegmentId()) ||
						(active != anonymousUserUserSegment.getActive())) {
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

			query.append(_SQL_SELECT_ANONYMOUSUSERUSERSEGMENT_WHERE);

			query.append(_FINDER_COLUMN_USERSEGMENTIDS_USERSEGMENTID_2);

			query.append(_FINDER_COLUMN_USERSEGMENTIDS_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AnonymousUserUserSegmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userSegmentId);

				qPos.add(active);

				if (!pagination) {
					list = (List<AnonymousUserUserSegment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AnonymousUserUserSegment>(list);
				}
				else {
					list = (List<AnonymousUserUserSegment>)QueryUtil.list(q,
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
	 * Returns the first anonymous user user segment in the ordered set where userSegmentId = &#63; and active = &#63;.
	 *
	 * @param userSegmentId the user segment ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment findByUserSegmentIds_First(
		long userSegmentId, boolean active, OrderByComparator orderByComparator)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = fetchByUserSegmentIds_First(userSegmentId,
				active, orderByComparator);

		if (anonymousUserUserSegment != null) {
			return anonymousUserUserSegment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userSegmentId=");
		msg.append(userSegmentId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymousUserUserSegmentException(msg.toString());
	}

	/**
	 * Returns the first anonymous user user segment in the ordered set where userSegmentId = &#63; and active = &#63;.
	 *
	 * @param userSegmentId the user segment ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymous user user segment, or <code>null</code> if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment fetchByUserSegmentIds_First(
		long userSegmentId, boolean active, OrderByComparator orderByComparator)
		throws SystemException {
		List<AnonymousUserUserSegment> list = findByUserSegmentIds(userSegmentId,
				active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last anonymous user user segment in the ordered set where userSegmentId = &#63; and active = &#63;.
	 *
	 * @param userSegmentId the user segment ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment findByUserSegmentIds_Last(
		long userSegmentId, boolean active, OrderByComparator orderByComparator)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = fetchByUserSegmentIds_Last(userSegmentId,
				active, orderByComparator);

		if (anonymousUserUserSegment != null) {
			return anonymousUserUserSegment;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userSegmentId=");
		msg.append(userSegmentId);

		msg.append(", active=");
		msg.append(active);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAnonymousUserUserSegmentException(msg.toString());
	}

	/**
	 * Returns the last anonymous user user segment in the ordered set where userSegmentId = &#63; and active = &#63;.
	 *
	 * @param userSegmentId the user segment ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymous user user segment, or <code>null</code> if a matching anonymous user user segment could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment fetchByUserSegmentIds_Last(
		long userSegmentId, boolean active, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByUserSegmentIds(userSegmentId, active);

		if (count == 0) {
			return null;
		}

		List<AnonymousUserUserSegment> list = findByUserSegmentIds(userSegmentId,
				active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the anonymous user user segments before and after the current anonymous user user segment in the ordered set where userSegmentId = &#63; and active = &#63;.
	 *
	 * @param anonymousUserUserSegmentId the primary key of the current anonymous user user segment
	 * @param userSegmentId the user segment ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a anonymous user user segment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment[] findByUserSegmentIds_PrevAndNext(
		long anonymousUserUserSegmentId, long userSegmentId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = findByPrimaryKey(anonymousUserUserSegmentId);

		Session session = null;

		try {
			session = openSession();

			AnonymousUserUserSegment[] array = new AnonymousUserUserSegmentImpl[3];

			array[0] = getByUserSegmentIds_PrevAndNext(session,
					anonymousUserUserSegment, userSegmentId, active,
					orderByComparator, true);

			array[1] = anonymousUserUserSegment;

			array[2] = getByUserSegmentIds_PrevAndNext(session,
					anonymousUserUserSegment, userSegmentId, active,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AnonymousUserUserSegment getByUserSegmentIds_PrevAndNext(
		Session session, AnonymousUserUserSegment anonymousUserUserSegment,
		long userSegmentId, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ANONYMOUSUSERUSERSEGMENT_WHERE);

		query.append(_FINDER_COLUMN_USERSEGMENTIDS_USERSEGMENTID_2);

		query.append(_FINDER_COLUMN_USERSEGMENTIDS_ACTIVE_2);

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
			query.append(AnonymousUserUserSegmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userSegmentId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(anonymousUserUserSegment);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AnonymousUserUserSegment> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the anonymous user user segments where userSegmentId = any &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userSegmentIds the user segment IDs
	 * @param active the active
	 * @return the matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByUserSegmentIds(
		long[] userSegmentIds, boolean active) throws SystemException {
		return findByUserSegmentIds(userSegmentIds, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the anonymous user user segments where userSegmentId = any &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userSegmentIds the user segment IDs
	 * @param active the active
	 * @param start the lower bound of the range of anonymous user user segments
	 * @param end the upper bound of the range of anonymous user user segments (not inclusive)
	 * @return the range of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByUserSegmentIds(
		long[] userSegmentIds, boolean active, int start, int end)
		throws SystemException {
		return findByUserSegmentIds(userSegmentIds, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the anonymous user user segments where userSegmentId = any &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userSegmentIds the user segment IDs
	 * @param active the active
	 * @param start the lower bound of the range of anonymous user user segments
	 * @param end the upper bound of the range of anonymous user user segments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findByUserSegmentIds(
		long[] userSegmentIds, boolean active, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if ((userSegmentIds != null) && (userSegmentIds.length == 1)) {
			return findByUserSegmentIds(userSegmentIds[0], active, start, end,
				orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(userSegmentIds), active };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(userSegmentIds), active,
					
					start, end, orderByComparator
				};
		}

		List<AnonymousUserUserSegment> list = (List<AnonymousUserUserSegment>)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_USERSEGMENTIDS,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AnonymousUserUserSegment anonymousUserUserSegment : list) {
				if (!ArrayUtil.contains(userSegmentIds,
							anonymousUserUserSegment.getUserSegmentId()) ||
						(active != anonymousUserUserSegment.getActive())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_ANONYMOUSUSERUSERSEGMENT_WHERE);

			boolean conjunctionable = false;

			if ((userSegmentIds == null) || (userSegmentIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < userSegmentIds.length; i++) {
					query.append(_FINDER_COLUMN_USERSEGMENTIDS_USERSEGMENTID_5);

					if ((i + 1) < userSegmentIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_USERSEGMENTIDS_ACTIVE_5);

			conjunctionable = true;

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AnonymousUserUserSegmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (userSegmentIds != null) {
					qPos.add(userSegmentIds);
				}

				qPos.add(active);

				if (!pagination) {
					list = (List<AnonymousUserUserSegment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AnonymousUserUserSegment>(list);
				}
				else {
					list = (List<AnonymousUserUserSegment>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_USERSEGMENTIDS,
					finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_USERSEGMENTIDS,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the anonymous user user segments where userSegmentId = &#63; and active = &#63; from the database.
	 *
	 * @param userSegmentId the user segment ID
	 * @param active the active
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUserSegmentIds(long userSegmentId, boolean active)
		throws SystemException {
		for (AnonymousUserUserSegment anonymousUserUserSegment : findByUserSegmentIds(
				userSegmentId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(anonymousUserUserSegment);
		}
	}

	/**
	 * Returns the number of anonymous user user segments where userSegmentId = &#63; and active = &#63;.
	 *
	 * @param userSegmentId the user segment ID
	 * @param active the active
	 * @return the number of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUserSegmentIds(long userSegmentId, boolean active)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERSEGMENTIDS;

		Object[] finderArgs = new Object[] { userSegmentId, active };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ANONYMOUSUSERUSERSEGMENT_WHERE);

			query.append(_FINDER_COLUMN_USERSEGMENTIDS_USERSEGMENTID_2);

			query.append(_FINDER_COLUMN_USERSEGMENTIDS_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userSegmentId);

				qPos.add(active);

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

	/**
	 * Returns the number of anonymous user user segments where userSegmentId = any &#63; and active = &#63;.
	 *
	 * @param userSegmentIds the user segment IDs
	 * @param active the active
	 * @return the number of matching anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUserSegmentIds(long[] userSegmentIds, boolean active)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				StringUtil.merge(userSegmentIds), active
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_USERSEGMENTIDS,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_ANONYMOUSUSERUSERSEGMENT_WHERE);

			boolean conjunctionable = false;

			if ((userSegmentIds == null) || (userSegmentIds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < userSegmentIds.length; i++) {
					query.append(_FINDER_COLUMN_USERSEGMENTIDS_USERSEGMENTID_5);

					if ((i + 1) < userSegmentIds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_USERSEGMENTIDS_ACTIVE_5);

			conjunctionable = true;

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (userSegmentIds != null) {
					qPos.add(userSegmentIds);
				}

				qPos.add(active);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_USERSEGMENTIDS,
					finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_USERSEGMENTIDS,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERSEGMENTIDS_USERSEGMENTID_2 = "anonymousUserUserSegment.userSegmentId = ? AND ";
	private static final String _FINDER_COLUMN_USERSEGMENTIDS_USERSEGMENTID_5 = "(" +
		removeConjunction(_FINDER_COLUMN_USERSEGMENTIDS_USERSEGMENTID_2) + ")";
	private static final String _FINDER_COLUMN_USERSEGMENTIDS_ACTIVE_2 = "anonymousUserUserSegment.active = ?";
	private static final String _FINDER_COLUMN_USERSEGMENTIDS_ACTIVE_5 = "(" +
		removeConjunction(_FINDER_COLUMN_USERSEGMENTIDS_ACTIVE_2) + ")";

	public AnonymousUserUserSegmentPersistenceImpl() {
		setModelClass(AnonymousUserUserSegment.class);
	}

	/**
	 * Caches the anonymous user user segment in the entity cache if it is enabled.
	 *
	 * @param anonymousUserUserSegment the anonymous user user segment
	 */
	@Override
	public void cacheResult(AnonymousUserUserSegment anonymousUserUserSegment) {
		EntityCacheUtil.putResult(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentImpl.class,
			anonymousUserUserSegment.getPrimaryKey(), anonymousUserUserSegment);

		anonymousUserUserSegment.resetOriginalValues();
	}

	/**
	 * Caches the anonymous user user segments in the entity cache if it is enabled.
	 *
	 * @param anonymousUserUserSegments the anonymous user user segments
	 */
	@Override
	public void cacheResult(
		List<AnonymousUserUserSegment> anonymousUserUserSegments) {
		for (AnonymousUserUserSegment anonymousUserUserSegment : anonymousUserUserSegments) {
			if (EntityCacheUtil.getResult(
						AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
						AnonymousUserUserSegmentImpl.class,
						anonymousUserUserSegment.getPrimaryKey()) == null) {
				cacheResult(anonymousUserUserSegment);
			}
			else {
				anonymousUserUserSegment.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all anonymous user user segments.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AnonymousUserUserSegmentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AnonymousUserUserSegmentImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the anonymous user user segment.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AnonymousUserUserSegment anonymousUserUserSegment) {
		EntityCacheUtil.removeResult(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentImpl.class,
			anonymousUserUserSegment.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<AnonymousUserUserSegment> anonymousUserUserSegments) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AnonymousUserUserSegment anonymousUserUserSegment : anonymousUserUserSegments) {
			EntityCacheUtil.removeResult(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
				AnonymousUserUserSegmentImpl.class,
				anonymousUserUserSegment.getPrimaryKey());
		}
	}

	/**
	 * Creates a new anonymous user user segment with the primary key. Does not add the anonymous user user segment to the database.
	 *
	 * @param anonymousUserUserSegmentId the primary key for the new anonymous user user segment
	 * @return the new anonymous user user segment
	 */
	@Override
	public AnonymousUserUserSegment create(long anonymousUserUserSegmentId) {
		AnonymousUserUserSegment anonymousUserUserSegment = new AnonymousUserUserSegmentImpl();

		anonymousUserUserSegment.setNew(true);
		anonymousUserUserSegment.setPrimaryKey(anonymousUserUserSegmentId);

		return anonymousUserUserSegment;
	}

	/**
	 * Removes the anonymous user user segment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param anonymousUserUserSegmentId the primary key of the anonymous user user segment
	 * @return the anonymous user user segment that was removed
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a anonymous user user segment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment remove(long anonymousUserUserSegmentId)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		return remove((Serializable)anonymousUserUserSegmentId);
	}

	/**
	 * Removes the anonymous user user segment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the anonymous user user segment
	 * @return the anonymous user user segment that was removed
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a anonymous user user segment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment remove(Serializable primaryKey)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AnonymousUserUserSegment anonymousUserUserSegment = (AnonymousUserUserSegment)session.get(AnonymousUserUserSegmentImpl.class,
					primaryKey);

			if (anonymousUserUserSegment == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAnonymousUserUserSegmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(anonymousUserUserSegment);
		}
		catch (NoSuchAnonymousUserUserSegmentException nsee) {
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
	protected AnonymousUserUserSegment removeImpl(
		AnonymousUserUserSegment anonymousUserUserSegment)
		throws SystemException {
		anonymousUserUserSegment = toUnwrappedModel(anonymousUserUserSegment);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(anonymousUserUserSegment)) {
				anonymousUserUserSegment = (AnonymousUserUserSegment)session.get(AnonymousUserUserSegmentImpl.class,
						anonymousUserUserSegment.getPrimaryKeyObj());
			}

			if (anonymousUserUserSegment != null) {
				session.delete(anonymousUserUserSegment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (anonymousUserUserSegment != null) {
			clearCache(anonymousUserUserSegment);
		}

		return anonymousUserUserSegment;
	}

	@Override
	public AnonymousUserUserSegment updateImpl(
		com.liferay.content.targeting.model.AnonymousUserUserSegment anonymousUserUserSegment)
		throws SystemException {
		anonymousUserUserSegment = toUnwrappedModel(anonymousUserUserSegment);

		boolean isNew = anonymousUserUserSegment.isNew();

		AnonymousUserUserSegmentModelImpl anonymousUserUserSegmentModelImpl = (AnonymousUserUserSegmentModelImpl)anonymousUserUserSegment;

		Session session = null;

		try {
			session = openSession();

			if (anonymousUserUserSegment.isNew()) {
				session.save(anonymousUserUserSegment);

				anonymousUserUserSegment.setNew(false);
			}
			else {
				session.merge(anonymousUserUserSegment);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AnonymousUserUserSegmentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((anonymousUserUserSegmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANONYMOUSUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						anonymousUserUserSegmentModelImpl.getOriginalAnonymousUserId(),
						anonymousUserUserSegmentModelImpl.getOriginalActive()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ANONYMOUSUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANONYMOUSUSERID,
					args);

				args = new Object[] {
						anonymousUserUserSegmentModelImpl.getAnonymousUserId(),
						anonymousUserUserSegmentModelImpl.getActive()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ANONYMOUSUSERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ANONYMOUSUSERID,
					args);
			}

			if ((anonymousUserUserSegmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_U.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						anonymousUserUserSegmentModelImpl.getOriginalAnonymousUserId(),
						anonymousUserUserSegmentModelImpl.getOriginalUserSegmentId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_U,
					args);

				args = new Object[] {
						anonymousUserUserSegmentModelImpl.getAnonymousUserId(),
						anonymousUserUserSegmentModelImpl.getUserSegmentId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_A_U, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_A_U,
					args);
			}

			if ((anonymousUserUserSegmentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERSEGMENTIDS.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						anonymousUserUserSegmentModelImpl.getOriginalUserSegmentId(),
						anonymousUserUserSegmentModelImpl.getOriginalActive()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERSEGMENTIDS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERSEGMENTIDS,
					args);

				args = new Object[] {
						anonymousUserUserSegmentModelImpl.getUserSegmentId(),
						anonymousUserUserSegmentModelImpl.getActive()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERSEGMENTIDS,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERSEGMENTIDS,
					args);
			}
		}

		EntityCacheUtil.putResult(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
			AnonymousUserUserSegmentImpl.class,
			anonymousUserUserSegment.getPrimaryKey(), anonymousUserUserSegment);

		return anonymousUserUserSegment;
	}

	protected AnonymousUserUserSegment toUnwrappedModel(
		AnonymousUserUserSegment anonymousUserUserSegment) {
		if (anonymousUserUserSegment instanceof AnonymousUserUserSegmentImpl) {
			return anonymousUserUserSegment;
		}

		AnonymousUserUserSegmentImpl anonymousUserUserSegmentImpl = new AnonymousUserUserSegmentImpl();

		anonymousUserUserSegmentImpl.setNew(anonymousUserUserSegment.isNew());
		anonymousUserUserSegmentImpl.setPrimaryKey(anonymousUserUserSegment.getPrimaryKey());

		anonymousUserUserSegmentImpl.setAnonymousUserUserSegmentId(anonymousUserUserSegment.getAnonymousUserUserSegmentId());
		anonymousUserUserSegmentImpl.setCompanyId(anonymousUserUserSegment.getCompanyId());
		anonymousUserUserSegmentImpl.setModifiedDate(anonymousUserUserSegment.getModifiedDate());
		anonymousUserUserSegmentImpl.setAnonymousUserId(anonymousUserUserSegment.getAnonymousUserId());
		anonymousUserUserSegmentImpl.setUserSegmentId(anonymousUserUserSegment.getUserSegmentId());
		anonymousUserUserSegmentImpl.setManual(anonymousUserUserSegment.isManual());
		anonymousUserUserSegmentImpl.setActive(anonymousUserUserSegment.isActive());

		return anonymousUserUserSegmentImpl;
	}

	/**
	 * Returns the anonymous user user segment with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the anonymous user user segment
	 * @return the anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a anonymous user user segment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = fetchByPrimaryKey(primaryKey);

		if (anonymousUserUserSegment == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAnonymousUserUserSegmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return anonymousUserUserSegment;
	}

	/**
	 * Returns the anonymous user user segment with the primary key or throws a {@link com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException} if it could not be found.
	 *
	 * @param anonymousUserUserSegmentId the primary key of the anonymous user user segment
	 * @return the anonymous user user segment
	 * @throws com.liferay.content.targeting.NoSuchAnonymousUserUserSegmentException if a anonymous user user segment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment findByPrimaryKey(
		long anonymousUserUserSegmentId)
		throws NoSuchAnonymousUserUserSegmentException, SystemException {
		return findByPrimaryKey((Serializable)anonymousUserUserSegmentId);
	}

	/**
	 * Returns the anonymous user user segment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the anonymous user user segment
	 * @return the anonymous user user segment, or <code>null</code> if a anonymous user user segment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		AnonymousUserUserSegment anonymousUserUserSegment = (AnonymousUserUserSegment)EntityCacheUtil.getResult(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
				AnonymousUserUserSegmentImpl.class, primaryKey);

		if (anonymousUserUserSegment == _nullAnonymousUserUserSegment) {
			return null;
		}

		if (anonymousUserUserSegment == null) {
			Session session = null;

			try {
				session = openSession();

				anonymousUserUserSegment = (AnonymousUserUserSegment)session.get(AnonymousUserUserSegmentImpl.class,
						primaryKey);

				if (anonymousUserUserSegment != null) {
					cacheResult(anonymousUserUserSegment);
				}
				else {
					EntityCacheUtil.putResult(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
						AnonymousUserUserSegmentImpl.class, primaryKey,
						_nullAnonymousUserUserSegment);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(AnonymousUserUserSegmentModelImpl.ENTITY_CACHE_ENABLED,
					AnonymousUserUserSegmentImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return anonymousUserUserSegment;
	}

	/**
	 * Returns the anonymous user user segment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param anonymousUserUserSegmentId the primary key of the anonymous user user segment
	 * @return the anonymous user user segment, or <code>null</code> if a anonymous user user segment with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AnonymousUserUserSegment fetchByPrimaryKey(
		long anonymousUserUserSegmentId) throws SystemException {
		return fetchByPrimaryKey((Serializable)anonymousUserUserSegmentId);
	}

	/**
	 * Returns all the anonymous user user segments.
	 *
	 * @return the anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<AnonymousUserUserSegment> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the anonymous user user segments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.AnonymousUserUserSegmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of anonymous user user segments
	 * @param end the upper bound of the range of anonymous user user segments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of anonymous user user segments
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AnonymousUserUserSegment> findAll(int start, int end,
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

		List<AnonymousUserUserSegment> list = (List<AnonymousUserUserSegment>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ANONYMOUSUSERUSERSEGMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ANONYMOUSUSERUSERSEGMENT;

				if (pagination) {
					sql = sql.concat(AnonymousUserUserSegmentModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AnonymousUserUserSegment>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<AnonymousUserUserSegment>(list);
				}
				else {
					list = (List<AnonymousUserUserSegment>)QueryUtil.list(q,
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
	 * Removes all the anonymous user user segments from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (AnonymousUserUserSegment anonymousUserUserSegment : findAll()) {
			remove(anonymousUserUserSegment);
		}
	}

	/**
	 * Returns the number of anonymous user user segments.
	 *
	 * @return the number of anonymous user user segments
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

				Query q = session.createQuery(_SQL_COUNT_ANONYMOUSUSERUSERSEGMENT);

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

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the anonymous user user segment persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.content.targeting.model.AnonymousUserUserSegment")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AnonymousUserUserSegment>> listenersList = new ArrayList<ModelListener<AnonymousUserUserSegment>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AnonymousUserUserSegment>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AnonymousUserUserSegmentImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_ANONYMOUSUSERUSERSEGMENT = "SELECT anonymousUserUserSegment FROM AnonymousUserUserSegment anonymousUserUserSegment";
	private static final String _SQL_SELECT_ANONYMOUSUSERUSERSEGMENT_WHERE = "SELECT anonymousUserUserSegment FROM AnonymousUserUserSegment anonymousUserUserSegment WHERE ";
	private static final String _SQL_COUNT_ANONYMOUSUSERUSERSEGMENT = "SELECT COUNT(anonymousUserUserSegment) FROM AnonymousUserUserSegment anonymousUserUserSegment";
	private static final String _SQL_COUNT_ANONYMOUSUSERUSERSEGMENT_WHERE = "SELECT COUNT(anonymousUserUserSegment) FROM AnonymousUserUserSegment anonymousUserUserSegment WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "anonymousUserUserSegment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AnonymousUserUserSegment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AnonymousUserUserSegment exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AnonymousUserUserSegmentPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"active"
			});
	private static AnonymousUserUserSegment _nullAnonymousUserUserSegment = new AnonymousUserUserSegmentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AnonymousUserUserSegment> toCacheModel() {
				return _nullAnonymousUserUserSegmentCacheModel;
			}
		};

	private static CacheModel<AnonymousUserUserSegment> _nullAnonymousUserUserSegmentCacheModel =
		new CacheModel<AnonymousUserUserSegment>() {
			@Override
			public AnonymousUserUserSegment toEntityModel() {
				return _nullAnonymousUserUserSegment;
			}
		};
}