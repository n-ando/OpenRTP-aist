package org.openrtp.namespaces;

import org.openrtp.namespaces.rtc.version03.RtcProfile;

public class RtcProfileHolder {
	private RtcProfile profile;
	
	public RtcProfileHolder(){
	}
	
	public RtcProfile getRtcProfile() {
		return profile;
	}
	public void setRtcProfile(RtcProfile profile) {
		this.profile = profile;
	}

}
