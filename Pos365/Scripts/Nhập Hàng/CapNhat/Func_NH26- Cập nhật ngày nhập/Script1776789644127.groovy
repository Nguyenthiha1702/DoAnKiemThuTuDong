import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.PurchasePage
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
PurchasePage page = new PurchasePage()
WebUI.callTestCase(findTestCase("common/login"), [:])
page.moManHinhNhapHang()
page.searchProductByMa(maCT)
page.clickCapNhat()
WebUI.setText(findTestObject('Nhập Hàng/ThemMoi/date_NgayNhap'), ngayNhap)
WebUI.delay(2)
page.clickSave()
WebUI.delay(1)
page.searchProductByMa(maCT)
WebUI.delay(2)
page.verifyNgay(ngayNhap)
WebUI.comment(" Update thành công nhà cung cấp")