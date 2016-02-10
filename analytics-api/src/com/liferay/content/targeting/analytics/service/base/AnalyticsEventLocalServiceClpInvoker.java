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

package com.liferay.content.targeting.analytics.service.base;

import com.liferay.content.targeting.analytics.service.AnalyticsEventLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AnalyticsEventLocalServiceClpInvoker {
	public AnalyticsEventLocalServiceClpInvoker() {
		_methodName0 = "addAnalyticsEvent";

		_methodParameterTypes0 = new String[] {
				"com.liferay.content.targeting.analytics.model.AnalyticsEvent"
			};

		_methodName1 = "createAnalyticsEvent";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAnalyticsEvent";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAnalyticsEvent";

		_methodParameterTypes3 = new String[] {
				"com.liferay.content.targeting.analytics.model.AnalyticsEvent"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchAnalyticsEvent";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getAnalyticsEvent";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getAnalyticsEvents";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getAnalyticsEventsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateAnalyticsEvent";

		_methodParameterTypes15 = new String[] {
				"com.liferay.content.targeting.analytics.model.AnalyticsEvent"
			};

		_methodName42 = "getBeanIdentifier";

		_methodParameterTypes42 = new String[] {  };

		_methodName43 = "setBeanIdentifier";

		_methodParameterTypes43 = new String[] { "java.lang.String" };

		_methodName48 = "addAnalyticsEvent";

		_methodParameterTypes48 = new String[] {
				"long", "long", "java.lang.String", "long", "java.util.Map",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName49 = "addAnalyticsEvent";

		_methodParameterTypes49 = new String[] {
				"long", "long", "java.lang.String", "long", "java.lang.String",
				"long[][]", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName50 = "addAnalyticsEvent";

		_methodParameterTypes50 = new String[] {
				"long", "long", "java.lang.String", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName51 = "checkAnalyticsEvents";

		_methodParameterTypes51 = new String[] {  };

		_methodName52 = "deleteAnalyticsEvent";

		_methodParameterTypes52 = new String[] {
				"com.liferay.content.targeting.analytics.model.AnalyticsEvent"
			};

		_methodName53 = "deleteAnalyticsEvents";

		_methodParameterTypes53 = new String[] { "long", "java.util.Date" };

		_methodName54 = "getAnalyticsEvents";

		_methodParameterTypes54 = new String[] { "long", "java.util.Date" };

		_methodName55 = "getAnalyticsEvents";

		_methodParameterTypes55 = new String[] {
				"long", "java.lang.String", "long", "java.util.Date"
			};

		_methodName56 = "getAnalyticsEvents";

		_methodParameterTypes56 = new String[] {
				"long", "java.lang.String", "long", "java.lang.String"
			};

		_methodName57 = "getAnalyticsEvents";

		_methodParameterTypes57 = new String[] {
				"java.lang.String", "long", "java.lang.String"
			};

		_methodName58 = "getAnalyticsEvents";

		_methodParameterTypes58 = new String[] {
				"java.lang.String", "long", "java.lang.String", "java.util.Date"
			};

		_methodName59 = "getAnalyticsEvents";

		_methodParameterTypes59 = new String[] {
				"java.lang.String", "java.lang.String", "java.util.Date"
			};

		_methodName60 = "getAnalyticsEventsContent";

		_methodParameterTypes60 = new String[] { "java.util.Date" };

		_methodName61 = "getAnalyticsEventsContentIds";

		_methodParameterTypes61 = new String[] { "java.util.Date" };

		_methodName62 = "getAnalyticsEventsCount";

		_methodParameterTypes62 = new String[] { "long", "java.util.Date" };

		_methodName63 = "getAnalyticsEventsCount";

		_methodParameterTypes63 = new String[] {
				"long", "java.lang.String", "long", "java.lang.String"
			};

		_methodName64 = "getAnalyticsEventsCount";

		_methodParameterTypes64 = new String[] {
				"java.lang.String", "long", "java.lang.String"
			};

		_methodName65 = "getAnalyticsEventsCount";

		_methodParameterTypes65 = new String[] {
				"java.lang.String", "long", "java.lang.String", "java.util.Date"
			};

		_methodName66 = "getAnalyticsEventsCount";

		_methodParameterTypes66 = new String[] {
				"java.lang.String", "long", "java.lang.String", "long",
				"java.lang.String", "java.util.Date"
			};

		_methodName67 = "getAnalyticsEventsCount";

		_methodParameterTypes67 = new String[] {
				"java.lang.String", "long", "java.lang.String",
				"java.lang.String", "java.util.Date"
			};

		_methodName68 = "getAnalyticsEventsCount";

		_methodParameterTypes68 = new String[] {
				"java.lang.String", "java.lang.String", "java.util.Date"
			};

		_methodName69 = "getAnalyticsEventsIds";

		_methodParameterTypes69 = new String[] {
				"java.lang.String", "long", "java.lang.String", "java.util.Date"
			};

		_methodName70 = "getAnalyticsEventsIds";

		_methodParameterTypes70 = new String[] {
				"java.lang.String", "java.lang.String", "java.util.Date"
			};

		_methodName71 = "getMaxAge";

		_methodParameterTypes71 = new String[] {  };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.addAnalyticsEvent((com.liferay.content.targeting.analytics.model.AnalyticsEvent)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.createAnalyticsEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.deleteAnalyticsEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.deleteAnalyticsEvent((com.liferay.content.targeting.analytics.model.AnalyticsEvent)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.fetchAnalyticsEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEvents(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEventsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.updateAnalyticsEvent((com.liferay.content.targeting.analytics.model.AnalyticsEvent)arguments[0]);
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			AnalyticsEventLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.addAnalyticsEvent(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.util.Map<java.lang.String, long[]>)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				(java.lang.String)arguments[9],
				(java.lang.String)arguments[10],
				(java.lang.String)arguments[11],
				(com.liferay.portal.service.ServiceContext)arguments[12]);
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.addAnalyticsEvent(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (long[])arguments[5],
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				(java.lang.String)arguments[8], (java.lang.String)arguments[9],
				(java.lang.String)arguments[10],
				(java.lang.String)arguments[11],
				(java.lang.String)arguments[12],
				(com.liferay.portal.service.ServiceContext)arguments[13]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.addAnalyticsEvent(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				(java.lang.String)arguments[8], (java.lang.String)arguments[9],
				(java.lang.String)arguments[10],
				(com.liferay.portal.service.ServiceContext)arguments[11]);
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			AnalyticsEventLocalServiceUtil.checkAnalyticsEvents();

			return null;
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.deleteAnalyticsEvent((com.liferay.content.targeting.analytics.model.AnalyticsEvent)arguments[0]);
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			AnalyticsEventLocalServiceUtil.deleteAnalyticsEvents(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1]);

			return null;
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEvents(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1]);
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEvents(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(), (java.util.Date)arguments[3]);
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEvents(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(), (java.lang.String)arguments[3]);
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEvents((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEvents((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.util.Date)arguments[3]);
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEvents((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.util.Date)arguments[2]);
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEventsContent((java.util.Date)arguments[0]);
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEventsContentIds((java.util.Date)arguments[0]);
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEventsCount(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1]);
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEventsCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(), (java.lang.String)arguments[3]);
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEventsCount((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEventsCount((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.util.Date)arguments[3]);
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEventsCount((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.util.Date)arguments[5]);
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEventsCount((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.util.Date)arguments[4]);
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEventsCount((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.util.Date)arguments[2]);
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEventsIds((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.util.Date)arguments[3]);
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getAnalyticsEventsIds((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.util.Date)arguments[2]);
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return AnalyticsEventLocalServiceUtil.getMaxAge();
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName48;
	private String[] _methodParameterTypes48;
	private String _methodName49;
	private String[] _methodParameterTypes49;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName54;
	private String[] _methodParameterTypes54;
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName64;
	private String[] _methodParameterTypes64;
	private String _methodName65;
	private String[] _methodParameterTypes65;
	private String _methodName66;
	private String[] _methodParameterTypes66;
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName70;
	private String[] _methodParameterTypes70;
	private String _methodName71;
	private String[] _methodParameterTypes71;
}