package com.pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.Keys

class LoginPage {

	
	static void ensureBrowser() {
		try {
			DriverFactory.getWebDriver()
			println("Browser already exists")
		} catch (Exception e) {
			println("Opening new browser...")
			WebUI.openBrowser('')
		}
	}

	static void safeScreenshot() {
		try {
			def driver = DriverFactory.getWebDriver()
			if (driver != null) {
				WebUI.takeScreenshot()
			}
		} catch (Exception ignored) {
			println("Skip screenshot (browser not available)")
		}
	}

	static void login(String url, String username, String password) {
		ensureBrowser()

		WebUI.navigateToUrl(url)
		WebUI.maximizeWindow()

		WebUI.setText(findTestObject('Đăng Nhập/txt_username'), username ?: '')
		WebUI.setText(findTestObject('Đăng Nhập/txt_password'), password ?: '')
		WebUI.click(findTestObject('Đăng Nhập/btn_quanly'))
	}

	
	static void loginFunction(String url, String username, String password,
			String loginType, String expected,
			String expectedMessage, String tc_id) {

		WebUI.comment("========== START TC: " + tc_id + " ==========")

		try {
			ensureBrowser()

			WebUI.navigateToUrl(url)
			WebUI.maximizeWindow()

			WebUI.setText(findTestObject('Đăng Nhập/txt_username'), username ?: '')
			WebUI.setText(findTestObject('Đăng Nhập/txt_password'), password ?: '')

			TestObject btnLogin

			switch (loginType) {
				case 'QUANLY':
					btnLogin = findTestObject('Đăng Nhập/btn_quanly')
					break
				case 'BANHANG':
					btnLogin = findTestObject('Đăng Nhập/btn_BanHang')
					break
				default:
					assert false : "LoginType không hợp lệ: " + loginType
			}

			WebUI.click(btnLogin)
			WebUI.delay(2)

			if (expected == 'SUCCESS') {

				def successObj = (loginType == 'QUANLY') ?
						findTestObject('Hàng hóa/Menu/menu_HangHoa') :
						findTestObject('Bán Hàng/KenhBanHang')

				boolean isSuccess = WebUI.waitForElementVisible(successObj, 5)
				assert isSuccess : "Không vào được trang"

			} else if (expected == 'FAIL') {

				def messErr = findTestObject('Đăng Nhập/mess_err')
				boolean isVisible = WebUI.waitForElementVisible(messErr, 5)

				if (!isVisible) {
					safeScreenshot()
					assert false : "Không thấy message lỗi"
				}

				String actualError = WebUI.getText(messErr).trim()

				if (expectedMessage?.trim()) {
					assert actualError.contains(expectedMessage)
				}

				WebUI.closeBrowser()
			}

		} catch (Exception e) {
			safeScreenshot()
			WebUI.comment("FAILED TC: " + tc_id)

			try { WebUI.closeBrowser() } catch (Exception ignored) {}

			throw e
		}
	}

	
	static void validateTextBox(String testObjectPath, String expectedValue, boolean shouldMatch) {
		try {
			TestObject obj = findTestObject(testObjectPath)

			WebUI.waitForElementVisible(obj, 10)
			WebUI.click(obj)

			WebUI.sendKeys(obj, Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(obj, Keys.chord(Keys.DELETE))

			WebUI.setText(obj, expectedValue)

			String actual = WebUI.getAttribute(obj, 'value')?.trim()
			boolean actualResult = actual == expectedValue.trim()

			assert actualResult == shouldMatch :
				"Textbox sai | expected=${expectedValue} | actual=${actual}"

		} catch (Exception e) {
			safeScreenshot()
			throw e
		}
	}
	static void validatePasswordLength(String testObjectPath, String input, boolean shouldMatch) {
		try {
			TestObject obj = findTestObject(testObjectPath)
	
			WebUI.waitForElementVisible(obj, 10)
	
			// nhập password
			WebUI.setText(obj, input)
	
			// lấy giá trị thực tế
			String actual = WebUI.getAttribute(obj, 'value') ?: ''
	
			int expectedLength = input.length()
			int actualLength = actual.length()
	
			boolean result = (actualLength == expectedLength)
	
			assert result == shouldMatch :
				"Password length sai | expected=${expectedLength}, actual=${actualLength}"
	
		} catch (Exception e) {
			safeScreenshot()
			throw e
		}
	}
}