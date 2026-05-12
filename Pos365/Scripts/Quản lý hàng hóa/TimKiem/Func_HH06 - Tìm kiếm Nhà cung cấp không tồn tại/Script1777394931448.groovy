import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.ProductPage

ProductPage productPage = new ProductPage()

WebUI.callTestCase(findTestCase('common/login'), [:])
productPage.navigateToList()

String maNCC = 'DH01aaaaa@@@@@'
productPage.searchProductByNCC(maNCC)

WebUI.delay(2)
WebUI.verifyElementVisible(findTestObject('Hàng hóa/ThemMoi/NhaCungCap/note_KhongXacDinh'))

WebUI.comment('Hiển thị cảnh báo nhà cung cấp không xác định')


