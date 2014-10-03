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

package com.liferay.content.targeting.report.campaign.newsletter.model.impl;

import com.liferay.content.targeting.report.campaign.newsletter.model.Newsletter;
import com.liferay.content.targeting.report.campaign.newsletter.model.NewsletterModel;
import com.liferay.content.targeting.report.campaign.newsletter.model.NewsletterSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Newsletter service. Represents a row in the &quot;ContentTargeting_CampaignNewsletter_Newsletter&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.content.targeting.report.campaign.newsletter.model.NewsletterModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link NewsletterImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NewsletterImpl
 * @see com.liferay.content.targeting.report.campaign.newsletter.model.Newsletter
 * @see com.liferay.content.targeting.report.campaign.newsletter.model.NewsletterModel
 * @generated
 */
@JSON(strict = true)
public class NewsletterModelImpl extends BaseModelImpl<Newsletter>
	implements NewsletterModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a newsletter model instance should use the {@link com.liferay.content.targeting.report.campaign.newsletter.model.Newsletter} interface instead.
	 */
	public static final String TABLE_NAME = "ContentTargeting_CampaignNewsletter_Newsletter";
	public static final Object[][] TABLE_COLUMNS = {
			{ "newsletterId", Types.BIGINT },
			{ "campaignId", Types.BIGINT },
			{ "alias_", Types.VARCHAR },
			{ "elementId", Types.VARCHAR },
			{ "eventType", Types.VARCHAR },
			{ "count", Types.INTEGER },
			{ "modifiedDate", Types.TIMESTAMP }
		};
	public static final String TABLE_SQL_CREATE = "create table ContentTargeting_CampaignNewsletter_Newsletter (newsletterId LONG not null primary key,campaignId LONG,alias_ VARCHAR(75) null,elementId VARCHAR(75) null,eventType VARCHAR(75) null,count INTEGER,modifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table ContentTargeting_CampaignNewsletter_Newsletter";
	public static final String ORDER_BY_JPQL = " ORDER BY newsletter.modifiedDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY ContentTargeting_CampaignNewsletter_Newsletter.modifiedDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.content.targeting.report.campaign.newsletter.model.Newsletter"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.content.targeting.report.campaign.newsletter.model.Newsletter"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.content.targeting.report.campaign.newsletter.model.Newsletter"),
			true);
	public static long ALIAS_COLUMN_BITMASK = 1L;
	public static long CAMPAIGNID_COLUMN_BITMASK = 2L;
	public static long ELEMENTID_COLUMN_BITMASK = 4L;
	public static long EVENTTYPE_COLUMN_BITMASK = 8L;
	public static long MODIFIEDDATE_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Newsletter toModel(NewsletterSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Newsletter model = new NewsletterImpl();

		model.setNewsletterId(soapModel.getNewsletterId());
		model.setCampaignId(soapModel.getCampaignId());
		model.setAlias(soapModel.getAlias());
		model.setElementId(soapModel.getElementId());
		model.setEventType(soapModel.getEventType());
		model.setCount(soapModel.getCount());
		model.setModifiedDate(soapModel.getModifiedDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Newsletter> toModels(NewsletterSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Newsletter> models = new ArrayList<Newsletter>(soapModels.length);

		for (NewsletterSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.content.targeting.report.campaign.newsletter.model.Newsletter"));

	public NewsletterModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _newsletterId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setNewsletterId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _newsletterId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Newsletter.class;
	}

	@Override
	public String getModelClassName() {
		return Newsletter.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("newsletterId", getNewsletterId());
		attributes.put("campaignId", getCampaignId());
		attributes.put("alias", getAlias());
		attributes.put("elementId", getElementId());
		attributes.put("eventType", getEventType());
		attributes.put("count", getCount());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long newsletterId = (Long)attributes.get("newsletterId");

		if (newsletterId != null) {
			setNewsletterId(newsletterId);
		}

		Long campaignId = (Long)attributes.get("campaignId");

		if (campaignId != null) {
			setCampaignId(campaignId);
		}

		String alias = (String)attributes.get("alias");

		if (alias != null) {
			setAlias(alias);
		}

		String elementId = (String)attributes.get("elementId");

		if (elementId != null) {
			setElementId(elementId);
		}

		String eventType = (String)attributes.get("eventType");

		if (eventType != null) {
			setEventType(eventType);
		}

		Integer count = (Integer)attributes.get("count");

		if (count != null) {
			setCount(count);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@JSON
	@Override
	public long getNewsletterId() {
		return _newsletterId;
	}

	@Override
	public void setNewsletterId(long newsletterId) {
		_newsletterId = newsletterId;
	}

	@JSON
	@Override
	public long getCampaignId() {
		return _campaignId;
	}

	@Override
	public void setCampaignId(long campaignId) {
		_columnBitmask |= CAMPAIGNID_COLUMN_BITMASK;

		if (!_setOriginalCampaignId) {
			_setOriginalCampaignId = true;

			_originalCampaignId = _campaignId;
		}

		_campaignId = campaignId;
	}

	public long getOriginalCampaignId() {
		return _originalCampaignId;
	}

	@JSON
	@Override
	public String getAlias() {
		if (_alias == null) {
			return StringPool.BLANK;
		}
		else {
			return _alias;
		}
	}

	@Override
	public void setAlias(String alias) {
		_columnBitmask |= ALIAS_COLUMN_BITMASK;

		if (_originalAlias == null) {
			_originalAlias = _alias;
		}

		_alias = alias;
	}

	public String getOriginalAlias() {
		return GetterUtil.getString(_originalAlias);
	}

	@JSON
	@Override
	public String getElementId() {
		if (_elementId == null) {
			return StringPool.BLANK;
		}
		else {
			return _elementId;
		}
	}

	@Override
	public void setElementId(String elementId) {
		_columnBitmask |= ELEMENTID_COLUMN_BITMASK;

		if (_originalElementId == null) {
			_originalElementId = _elementId;
		}

		_elementId = elementId;
	}

	public String getOriginalElementId() {
		return GetterUtil.getString(_originalElementId);
	}

	@JSON
	@Override
	public String getEventType() {
		if (_eventType == null) {
			return StringPool.BLANK;
		}
		else {
			return _eventType;
		}
	}

	@Override
	public void setEventType(String eventType) {
		_columnBitmask |= EVENTTYPE_COLUMN_BITMASK;

		if (_originalEventType == null) {
			_originalEventType = _eventType;
		}

		_eventType = eventType;
	}

	public String getOriginalEventType() {
		return GetterUtil.getString(_originalEventType);
	}

	@JSON
	@Override
	public int getCount() {
		return _count;
	}

	@Override
	public void setCount(int count) {
		_count = count;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_columnBitmask = -1L;

		_modifiedDate = modifiedDate;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Newsletter.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Newsletter toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Newsletter)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		NewsletterImpl newsletterImpl = new NewsletterImpl();

		newsletterImpl.setNewsletterId(getNewsletterId());
		newsletterImpl.setCampaignId(getCampaignId());
		newsletterImpl.setAlias(getAlias());
		newsletterImpl.setElementId(getElementId());
		newsletterImpl.setEventType(getEventType());
		newsletterImpl.setCount(getCount());
		newsletterImpl.setModifiedDate(getModifiedDate());

		newsletterImpl.resetOriginalValues();

		return newsletterImpl;
	}

	@Override
	public int compareTo(Newsletter newsletter) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				newsletter.getModifiedDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Newsletter)) {
			return false;
		}

		Newsletter newsletter = (Newsletter)obj;

		long primaryKey = newsletter.getPrimaryKey();

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
	public void resetOriginalValues() {
		NewsletterModelImpl newsletterModelImpl = this;

		newsletterModelImpl._originalCampaignId = newsletterModelImpl._campaignId;

		newsletterModelImpl._setOriginalCampaignId = false;

		newsletterModelImpl._originalAlias = newsletterModelImpl._alias;

		newsletterModelImpl._originalElementId = newsletterModelImpl._elementId;

		newsletterModelImpl._originalEventType = newsletterModelImpl._eventType;

		newsletterModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Newsletter> toCacheModel() {
		NewsletterCacheModel newsletterCacheModel = new NewsletterCacheModel();

		newsletterCacheModel.newsletterId = getNewsletterId();

		newsletterCacheModel.campaignId = getCampaignId();

		newsletterCacheModel.alias = getAlias();

		String alias = newsletterCacheModel.alias;

		if ((alias != null) && (alias.length() == 0)) {
			newsletterCacheModel.alias = null;
		}

		newsletterCacheModel.elementId = getElementId();

		String elementId = newsletterCacheModel.elementId;

		if ((elementId != null) && (elementId.length() == 0)) {
			newsletterCacheModel.elementId = null;
		}

		newsletterCacheModel.eventType = getEventType();

		String eventType = newsletterCacheModel.eventType;

		if ((eventType != null) && (eventType.length() == 0)) {
			newsletterCacheModel.eventType = null;
		}

		newsletterCacheModel.count = getCount();

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			newsletterCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			newsletterCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		return newsletterCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{newsletterId=");
		sb.append(getNewsletterId());
		sb.append(", campaignId=");
		sb.append(getCampaignId());
		sb.append(", alias=");
		sb.append(getAlias());
		sb.append(", elementId=");
		sb.append(getElementId());
		sb.append(", eventType=");
		sb.append(getEventType());
		sb.append(", count=");
		sb.append(getCount());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.content.targeting.report.campaign.newsletter.model.Newsletter");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>newsletterId</column-name><column-value><![CDATA[");
		sb.append(getNewsletterId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>campaignId</column-name><column-value><![CDATA[");
		sb.append(getCampaignId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>alias</column-name><column-value><![CDATA[");
		sb.append(getAlias());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>elementId</column-name><column-value><![CDATA[");
		sb.append(getElementId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>eventType</column-name><column-value><![CDATA[");
		sb.append(getEventType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>count</column-name><column-value><![CDATA[");
		sb.append(getCount());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Newsletter.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			Newsletter.class
		};
	private long _newsletterId;
	private long _campaignId;
	private long _originalCampaignId;
	private boolean _setOriginalCampaignId;
	private String _alias;
	private String _originalAlias;
	private String _elementId;
	private String _originalElementId;
	private String _eventType;
	private String _originalEventType;
	private int _count;
	private Date _modifiedDate;
	private long _columnBitmask;
	private Newsletter _escapedModel;
}