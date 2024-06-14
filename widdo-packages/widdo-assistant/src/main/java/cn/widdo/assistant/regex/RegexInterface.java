package cn.widdo.assistant.regex;

import java.util.regex.Pattern;

/**
 * 正则接口. 使用接口对枚举进行分类.
 *
 * @author XYL
 * @date 2022/11/30 1:16
 * @since 263.1.1.0
 */
@SuppressWarnings("ALL")
public interface RegexInterface {

	/**
	 * 正则匹配方法.
	 * @param input 待校验内容
	 * @return boolean
	 * @author XYL
	 * @date 2022/11/30 01:35:11
	 **/
	boolean matched(String input);

	/**
	 * 手机卡正则.
	 */
	enum SIMCard implements RegexInterface {

		/**
		 * 手机号.
		 */
		ALL("^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[35678]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[189]\\d{2}|66\\d{2})\\d{6}$") {
			@Override
			public boolean matched(String input) {
				return Pattern.matches(ALL.regex, input);
			}
		},

		/**
		 * 中国联通.
		 */
		CHINA_UNICOM("^(?:\\+?86)?1(?:3[0-2]|[578][56]|66)\\d{8}$") {
			@Override
			public boolean matched(String input) {
				return Pattern.matches(CHINA_UNICOM.regex, input);
			}
		},

		/**
		 * 中国移动.
		 */
		CHINA_MOBILE("^(?:\\+?86)?1(?:3(?:4[^9\\D]|[5-9]\\d)|5[^3-6\\D]\\d|8[23478]\\d|(?:78|98)\\d)\\d{7}$") {
			@Override
			public boolean matched(String input) {
				return Pattern.matches(CHINA_MOBILE.regex, input);
			}
		},

		/**
		 * 中国电信.
		 */
		CHINA_TELECOM(
				"^(?:\\+?86)?1(?:3(?:3\\d|49)\\d|53\\d{2}|8[019]\\d{2}|7(?:[37]\\d{2}|40[0-5])|9[19]\\d{2})\\d{6}$") {
			@Override
			public boolean matched(String input) {
				return Pattern.matches(CHINA_TELECOM.regex, input);
			}
		},

		/**
		 * 电话号码，带区号.可以用下面代替. ^((\d{3,4}-)|\d{3,4}-)?\d{7,8}$
		 */
		TELEPHONE("^[0][1-9]{2,3}-[0-9]{5,10}$") {
			@Override
			public boolean matched(String input) {
				return Pattern.matches(TELEPHONE.regex, input);
			}
		},

		/**
		 * 电话号码.可以用下面代替. ^((\d{3,4}-)|\d{3,4}-)?\d{7,8}$
		 */
		TELEPHONE_WITHOUT_AREA_CODE("[1-9]{1}[0-9]{5,8}$") {
			@Override
			public boolean matched(String input) {
				return Pattern.matches(TELEPHONE_WITHOUT_AREA_CODE.regex, input);
			}
		};

		/**
		 * 正则内容.
		 */
		private final String regex;

		/**
		 * constructor has one params.
		 * @param regex 正则表达式
		 */
		SIMCard(final String regex) {
			this.regex = regex;
		}

	}

	/**
	 * 邮箱.
	 *
	 * @author XYL
	 * @date 2022/11/30 13:50:57
	 **/
	enum Email implements RegexInterface {

		/**
		 * email.
		 */
		EMAIL("") {
			@Override
			public boolean matched(String input) {
				return Pattern.matches(EMAIL.regex, input);
			}
		};

		/**
		 * 正则.
		 */
		private final String regex;

		/**
		 * constructor has one params.
		 * @param regex 正则表达式
		 */
		Email(final String regex) {
			this.regex = regex;
		}

	}

}
