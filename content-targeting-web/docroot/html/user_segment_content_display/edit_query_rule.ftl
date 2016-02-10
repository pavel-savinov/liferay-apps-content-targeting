<#--
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
-->

<#include "../init.ftl" />
<#include "../macros.ftl" />

<#assign index = paramUtil.getInteger(request, "index", queryRule.getIndex())>

<#assign isFirst = getterUtil.getBoolean(request.getAttribute("configuration.isFirst"))>

<div class="field-row query-row">
	<#if !queryRule.isDefaultRule()>
		<@aui["input"] name="queryIndex${index}" type="hidden" />
	</#if>

	<#assign fullViewClass = "">
	<#assign summaryViewClass = "hide">

	<#if queryRule.isValid() && !isFirst >
		<#assign fullViewClass = "hide">
		<#assign summaryViewClass = "">
	</#if>

	<#if !queryRule.isDefaultRule()>
		<div class="full-view ${fullViewClass}">
			<@aui["column"] columnWidth=15>
				<span class="query-contains-text"><@liferay_ui["message"] key="if-the-user" /></span>

				<@aui["input"] checked=queryRule.isContains() label="belongs" name="queryContains${index}" type="radio" value=true />

				<@aui["input"] checked=!queryRule.isContains() label="does-not-belong" name="queryContains${index}" type="radio" value=false />
			</@>

			<@aui["column"] columnWidth=15>
				<span class="query-and-operator-text"><@liferay_ui["message"] key="to" /></span>

				<@aui["input"] checked=!queryRule.isAndOperator() label="any" name="queryAndOperator${index}" type="radio" value=false />

				<@aui["input"] checked=queryRule.isAndOperator() label="all" name="queryAndOperator${index}" type="radio" value=true />
			</@>

			<@aui["column"] columnWidth=30>
				<div class="user-segment-selector">
					<span class="query-and-operator-text"><@liferay_ui["message"] key="of-the-following-user-segments" /></span>

					<div class="lfr-tags-selector-content" id="<@portlet["namespace"] />assetCategoriesSelector${index}">
						<@aui["input"] name="userSegmentAssetCategoryIds${index}" type="hidden" value="${queryRule.getUserSegmentAssetCategoryIdsAsString()}" />
					</div>

					<@aui["script"] use="liferay-asset-categories-selector">
						var assetCategoriesSelector = new Liferay.AssetCategoriesSelector(
							{
								contentBox: '#<@portlet["namespace"] />assetCategoriesSelector${index}',
								curEntries: '${queryRule.getUserSegmentAssetCategoryNames(locale)}',
								curEntryIds: '${queryRule.getUserSegmentAssetCategoryIdsAsString()}',
								hiddenInput: '#<@portlet["namespace"] />userSegmentAssetCategoryIds${index}',
								instanceVar: '<@portlet["namespace"] />',
								vocabularyGroupIds: '${vocabularyGroupIds}',
								vocabularyIds: '${vocabularyIds}',
								title: '<@liferay_ui["message"] key="select-user-segments" />'
							}
						).render();

						var changeTitle = function() {
							assetCategoriesSelector._popup.titleNode.html(assetCategoriesSelector.get('title'));
						};

						A.Do.after(changeTitle, assetCategoriesSelector, '_showSelectPopup');
					</@>
				</div>
			</@>

			<@aui["column"] columnWidth=40>
				<div class="select-asset-selector">
					<span class="query-and-operator-text"><@liferay_ui["message"] key="display-this-content" /></span>

					<@renderAssetEntrySelector queryRule=queryRule index=index />
				</div>
			</@>
		</div>

		<#if queryRule.isValid()>
			<div class="summary-view ${summaryViewClass}">
				<@aui["column"] columnWidth=15>
					<span class="query-contains-text"><@liferay_ui["message"] key="if-the-user" /></span>:
					<span class="query-contains-value">
						<#if queryRule.isContains()>
							<@liferay_ui["message"] key="belongs" />
						<#else>
							<@liferay_ui["message"] key="does-not-belong" />
						</#if>
					</span>
				</@>

				<@aui["column"] columnWidth=15>
					<span class="query-and-operator-text"><@liferay_ui["message"] key="to" /></span>:
					<span class="query-and-operator-value">
						<#if queryRule.isAndOperator()>
							<@liferay_ui["message"] key="all" />
						<#else>
							<@liferay_ui["message"] key="any" />
						</#if>
					</span>
				</@>

				<@aui["column"] columnWidth=30>
					<span class="query-content-text"><@liferay_ui["message"] key="of-the-following-user-segments" /></span>
					<span class="query-content-value">${queryRule.getUserSegmentNames(locale)}</span>
				</@>

				<@aui["column"] columnWidth=40>
					<span class="query-content-text"><@liferay_ui["message"] key="display-this-content" /></span>
					<span class="query-content-value">${queryRule.getAssetTitle()} (<span class="query-content-value-type">${queryRule.getAssetType()}</span>)</span>
				</@>
			</div>
		</#if>
	<#else>
		<@renderDefaultQueryRule queryRule=queryRule />
	</#if>
</div>