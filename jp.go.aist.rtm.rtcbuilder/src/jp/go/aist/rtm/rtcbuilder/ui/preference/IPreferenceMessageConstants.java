package jp.go.aist.rtm.rtcbuilder.ui.preference;

import jp.go.aist.rtm.rtcbuilder.nl.Messages;
import jp.go.aist.rtm.rtcbuilder.util.StringUtil;

public interface IPreferenceMessageConstants {
	public static final String DOCUMENT_LBL_AUTHOR = Messages.getString("IPreferenceMessageConstants.DOCUMENT_LBL_AUTHOR"); //$NON-NLS-1$
	public static final String DOCUMENT_LBL_LICENSE_P1 = Messages.getString("IPreferenceMessageConstants.DOCUMENT_LBL_LICENSE_P1"); //$NON-NLS-1$
	public static final String DOCUMENT_LBL_LICENSE_P2 = Messages.getString("IPreferenceMessageConstants.DOCUMENT_LBL_LICENSE_P2"); //$NON-NLS-1$
	public static final String DOCUMENT_LBL_LICENSE = StringUtil.connectMessageWithSepalator( new String[]{DOCUMENT_LBL_LICENSE_P1, DOCUMENT_LBL_LICENSE_P2});

	public static final String PORT_TITLE_EVENT_PORT = Messages.getString("IPreferenceMessageConstants.PORT_TITLE_EVENT_PORT"); //$NON-NLS-1$
	//
	public static final String PORT_LBL_PREFIX = Messages.getString("IPreferenceMessageConstants.PREFIX"); //$NON-NLS-1$
	public static final String PORT_LBL_SUFFIX = Messages.getString("IPreferenceMessageConstants.SUFFIX"); //$NON-NLS-1$



}
