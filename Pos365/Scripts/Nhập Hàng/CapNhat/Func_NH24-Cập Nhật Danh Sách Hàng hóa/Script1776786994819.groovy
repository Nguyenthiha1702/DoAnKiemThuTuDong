import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.PurchasePage

PurchasePage page = new PurchasePage()
String maCT = "NH190426-0001"
WebUI.callTestCase(findTestCase("common/login"), [:])

page.moManHinhNhapHang()
page.searchProductByMa(maCT)
page.clickCapNhat()
page.themSanPhamDayDu("Bánh cosy", "5", "100000")

def expectedList = page.getDanhSachHangHoa()
WebUI.comment("Expected list: " + expectedList)
WebUI.delay(2)
page.clickSave()
WebUI.delay(1)
page.searchProductByMa(maCT)
WebUI.delay(2)
page.verifyDanhSachHangHoa(expectedList)

WebUI.comment(" Update thành công danh sách hàng hóa")