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

package com.liferay.content.targeting.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.content.targeting.model.ChannelInstance;
import com.liferay.content.targeting.model.ChannelInstanceModel;
import com.liferay.content.targeting.model.ChannelInstanceSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the ChannelInstance service. Represents a row in the &quot;CT_ChannelInstance&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ChannelInstanceModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ChannelInstanceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ChannelInstanceImpl
 * @see ChannelInstance
 * @see ChannelInstanceModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class ChannelInstanceModelImpl extends BaseModelImpl<ChannelInstance>
	implements ChannelInstanceModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a channel instance model instance should use the {@link ChannelInstance} interface instead.
	 */
	public static final String TABLE_NAME = "CT_ChannelInstance";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "channelInstanceId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "channelKey", Types.VARCHAR },
			{ "campaignId", Types.BIGINT },
			{ "tacticId", Types.BIGINT },
			{ "alias_", Types.VARCHAR },
			{ "typeSettings", Types.CLOB }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("channelInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("channelKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("campaignId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("tacticId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("alias_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("typeSettings", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE = "create table CT_ChannelInstance (uuid_ VARCHAR(75) null,channelInstanceId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,channelKey VARCHAR(75) null,campaignId LONG,tacticId LONG,alias_ VARCHAR(75) null,typeSettings TEXT null)";
	public static final String TABLE_SQL_DROP = "drop table CT_ChannelInstance";
	public static final String ORDER_BY_JPQL = " ORDER BY channelInstance.channelKey DESC";
	public static final String ORDER_BY_SQL = " ORDER BY CT_ChannelInstance.channelKey DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.content.targeting.api.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.content.targeting.model.ChannelInstance"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.content.targeting.api.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.content.targeting.model.ChannelInstance"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.content.targeting.api.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.content.targeting.model.ChannelInstance"),
			true);
	public static final long ALIAS_COLUMN_BITMASK = 1L;
	public static final long CAMPAIGNID_COLUMN_BITMASK = 2L;
	public static final long CHANNELKEY_COLUMN_BITMASK = 4L;
	public static final long COMPANYID_COLUMN_BITMASK = 8L;
	public static final long GROUPID_COLUMN_BITMASK = 16L;
	public static final long TACTICID_COLUMN_BITMASK = 32L;
	public static final long UUID_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static ChannelInstance toModel(ChannelInstanceSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ChannelInstance model = new ChannelInstanceImpl();

		model.setUuid(soapModel.getUuid());
		model.setChannelInstanceId(soapModel.getChannelInstanceId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setChannelKey(soapModel.getChannelKey());
		model.setCampaignId(soapModel.getCampaignId());
		model.setTacticId(soapModel.getTacticId());
		model.setAlias(soapModel.getAlias());
		model.setTypeSettings(soapModel.getTypeSettings());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ChannelInstance> toModels(
		ChannelInstanceSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<ChannelInstance> models = new ArrayList<ChannelInstance>(soapModels.length);

		for (ChannelInstanceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.content.targeting.api.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.content.targeting.model.ChannelInstance"));

	public ChannelInstanceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _channelInstanceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setChannelInstanceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _channelInstanceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ChannelInstance.class;
	}

	@Override
	public String getModelClassName() {
		return ChannelInstance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("channelInstanceId", getChannelInstanceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("channelKey", getChannelKey());
		attributes.put("campaignId", getCampaignId());
		attributes.put("tacticId", getTacticId());
		attributes.put("alias", getAlias());
		attributes.put("typeSettings", getTypeSettings());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long channelInstanceId = (Long)attributes.get("channelInstanceId");

		if (channelInstanceId != null) {
			setChannelInstanceId(channelInstanceId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String channelKey = (String)attributes.get("channelKey");

		if (channelKey != null) {
			setChannelKey(channelKey);
		}

		Long campaignId = (Long)attributes.get("campaignId");

		if (campaignId != null) {
			setCampaignId(campaignId);
		}

		Long tacticId = (Long)attributes.get("tacticId");

		if (tacticId != null) {
			setTacticId(tacticId);
		}

		String alias = (String)attributes.get("alias");

		if (alias != null) {
			setAlias(alias);
		}

		String typeSettings = (String)attributes.get("typeSettings");

		if (typeSettings != null) {
			setTypeSettings(typeSettings);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getChannelInstanceId() {
		return _channelInstanceId;
	}

	@Override
	public void setChannelInstanceId(long channelInstanceId) {
		_channelInstanceId = channelInstanceId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getChannelKey() {
		if (_channelKey == null) {
			return StringPool.BLANK;
		}
		else {
			return _channelKey;
		}
	}

	@Override
	public void setChannelKey(String channelKey) {
		_columnBitmask = -1L;

		if (_originalChannelKey == null) {
			_originalChannelKey = _channelKey;
		}

		_channelKey = channelKey;
	}

	public String getOriginalChannelKey() {
		return GetterUtil.getString(_originalChannelKey);
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
	public long getTacticId() {
		return _tacticId;
	}

	@Override
	public void setTacticId(long tacticId) {
		_columnBitmask |= TACTICID_COLUMN_BITMASK;

		if (!_setOriginalTacticId) {
			_setOriginalTacticId = true;

			_originalTacticId = _tacticId;
		}

		_tacticId = tacticId;
	}

	public long getOriginalTacticId() {
		return _originalTacticId;
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
	public String getTypeSettings() {
		if (_typeSettings == null) {
			return StringPool.BLANK;
		}
		else {
			return _typeSettings;
		}
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				ChannelInstance.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			ChannelInstance.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ChannelInstance toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ChannelInstance)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ChannelInstanceImpl channelInstanceImpl = new ChannelInstanceImpl();

		channelInstanceImpl.setUuid(getUuid());
		channelInstanceImpl.setChannelInstanceId(getChannelInstanceId());
		channelInstanceImpl.setGroupId(getGroupId());
		channelInstanceImpl.setCompanyId(getCompanyId());
		channelInstanceImpl.setUserId(getUserId());
		channelInstanceImpl.setUserName(getUserName());
		channelInstanceImpl.setCreateDate(getCreateDate());
		channelInstanceImpl.setModifiedDate(getModifiedDate());
		channelInstanceImpl.setChannelKey(getChannelKey());
		channelInstanceImpl.setCampaignId(getCampaignId());
		channelInstanceImpl.setTacticId(getTacticId());
		channelInstanceImpl.setAlias(getAlias());
		channelInstanceImpl.setTypeSettings(getTypeSettings());

		channelInstanceImpl.resetOriginalValues();

		return channelInstanceImpl;
	}

	@Override
	public int compareTo(ChannelInstance channelInstance) {
		int value = 0;

		value = getChannelKey().compareTo(channelInstance.getChannelKey());

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

		if (!(obj instanceof ChannelInstance)) {
			return false;
		}

		ChannelInstance channelInstance = (ChannelInstance)obj;

		long primaryKey = channelInstance.getPrimaryKey();

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
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		ChannelInstanceModelImpl channelInstanceModelImpl = this;

		channelInstanceModelImpl._originalUuid = channelInstanceModelImpl._uuid;

		channelInstanceModelImpl._originalGroupId = channelInstanceModelImpl._groupId;

		channelInstanceModelImpl._setOriginalGroupId = false;

		channelInstanceModelImpl._originalCompanyId = channelInstanceModelImpl._companyId;

		channelInstanceModelImpl._setOriginalCompanyId = false;

		channelInstanceModelImpl._setModifiedDate = false;

		channelInstanceModelImpl._originalChannelKey = channelInstanceModelImpl._channelKey;

		channelInstanceModelImpl._originalCampaignId = channelInstanceModelImpl._campaignId;

		channelInstanceModelImpl._setOriginalCampaignId = false;

		channelInstanceModelImpl._originalTacticId = channelInstanceModelImpl._tacticId;

		channelInstanceModelImpl._setOriginalTacticId = false;

		channelInstanceModelImpl._originalAlias = channelInstanceModelImpl._alias;

		channelInstanceModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ChannelInstance> toCacheModel() {
		ChannelInstanceCacheModel channelInstanceCacheModel = new ChannelInstanceCacheModel();

		channelInstanceCacheModel.uuid = getUuid();

		String uuid = channelInstanceCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			channelInstanceCacheModel.uuid = null;
		}

		channelInstanceCacheModel.channelInstanceId = getChannelInstanceId();

		channelInstanceCacheModel.groupId = getGroupId();

		channelInstanceCacheModel.companyId = getCompanyId();

		channelInstanceCacheModel.userId = getUserId();

		channelInstanceCacheModel.userName = getUserName();

		String userName = channelInstanceCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			channelInstanceCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			channelInstanceCacheModel.createDate = createDate.getTime();
		}
		else {
			channelInstanceCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			channelInstanceCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			channelInstanceCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		channelInstanceCacheModel.channelKey = getChannelKey();

		String channelKey = channelInstanceCacheModel.channelKey;

		if ((channelKey != null) && (channelKey.length() == 0)) {
			channelInstanceCacheModel.channelKey = null;
		}

		channelInstanceCacheModel.campaignId = getCampaignId();

		channelInstanceCacheModel.tacticId = getTacticId();

		channelInstanceCacheModel.alias = getAlias();

		String alias = channelInstanceCacheModel.alias;

		if ((alias != null) && (alias.length() == 0)) {
			channelInstanceCacheModel.alias = null;
		}

		channelInstanceCacheModel.typeSettings = getTypeSettings();

		String typeSettings = channelInstanceCacheModel.typeSettings;

		if ((typeSettings != null) && (typeSettings.length() == 0)) {
			channelInstanceCacheModel.typeSettings = null;
		}

		return channelInstanceCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", channelInstanceId=");
		sb.append(getChannelInstanceId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", channelKey=");
		sb.append(getChannelKey());
		sb.append(", campaignId=");
		sb.append(getCampaignId());
		sb.append(", tacticId=");
		sb.append(getTacticId());
		sb.append(", alias=");
		sb.append(getAlias());
		sb.append(", typeSettings=");
		sb.append(getTypeSettings());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.content.targeting.model.ChannelInstance");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>channelInstanceId</column-name><column-value><![CDATA[");
		sb.append(getChannelInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>channelKey</column-name><column-value><![CDATA[");
		sb.append(getChannelKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>campaignId</column-name><column-value><![CDATA[");
		sb.append(getCampaignId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tacticId</column-name><column-value><![CDATA[");
		sb.append(getTacticId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>alias</column-name><column-value><![CDATA[");
		sb.append(getAlias());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>typeSettings</column-name><column-value><![CDATA[");
		sb.append(getTypeSettings());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ChannelInstance.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ChannelInstance.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _channelInstanceId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _channelKey;
	private String _originalChannelKey;
	private long _campaignId;
	private long _originalCampaignId;
	private boolean _setOriginalCampaignId;
	private long _tacticId;
	private long _originalTacticId;
	private boolean _setOriginalTacticId;
	private String _alias;
	private String _originalAlias;
	private String _typeSettings;
	private long _columnBitmask;
	private ChannelInstance _escapedModel;
}