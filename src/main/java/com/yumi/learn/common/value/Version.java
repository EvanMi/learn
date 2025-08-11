package com.yumi.learn.common.value;

/**
 * (3 多版本API)
 */
public record Version(VersionEnum versionEnum) {
	/***
	 * &lt;type&gt;/&lt;tree&gt;.&lt;subtype&gt;+&lt;suffix&gt;[;参数…] </br>
	 * 1.type：application、audio、example、image、message、model、multipart、text、video </br>
	 * 2. tree：vnd </br>
	 * 3. subtype：yumi.v[x] 4. suffix：json 5. 参数：charset=utf-8 </br>
	 */
	public static final String v1VersionAcceptStr = "application/vnd.yumi.v1+json";

	public static final String v2VersionAcceptStr = "application/vnd.yumi.v2+json";

	public static final String v3VersionAcceptStr = "application/vnd.yumi.v3+json";

	public static final String stableVersionAcceptStr = "application/vnd.yumi.stable+json";

	public static final String latestVersionAcceptStr = "application/vnd.yumi.latest+json";

	/**
	 * 稳定版本
	 */
	public static Version STABLE = new Version(VersionEnum.V2);

	/**
	 * 最新版本
	 */
	public static Version LATEST = new Version(VersionEnum.V3);

	public static Version ofCode(String versionCode) {
		return new Version(VersionEnum.fromCode(versionCode));
	}

	public enum VersionEnum {

		V1("v1", v1VersionAcceptStr, true), V2("v2", v2VersionAcceptStr, false), V3("v3", v3VersionAcceptStr, false),;

		VersionEnum(String code, String accept, Boolean deprecated) {
			this.code = code;
			this.accept = accept;
			this.deprecated = deprecated;
		}

		private final String code;

		private final String accept;

		private final Boolean deprecated;

		public String getCode() {
			return code;
		}

		public String getAccept() {
			return accept;
		}

		public Boolean getDeprecated() {
			return deprecated;
		}

		public static VersionEnum fromAccept(String accept) {
			for (VersionEnum versionEnum : values()) {
				if (versionEnum.getAccept().equals(accept)) {
					return versionEnum;
				}
			}
			return Version.STABLE.versionEnum;
		}

		public static VersionEnum fromCode(String code) {
			for (VersionEnum versionEnum : values()) {
				if (versionEnum.getCode().equals(code)) {
					return versionEnum;
				}
			}
			return Version.STABLE.versionEnum;
		}

	}
}
