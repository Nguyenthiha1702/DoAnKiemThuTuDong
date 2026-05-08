import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.PurchasePage

PurchasePage page = new PurchasePage()
WebUI.callTestCase(findTestCase("common/login"), [:])
page.moManHinhNhapHang()
page.searchProductByMa(maCT)
page.clickCapNhat()
page.searchNCCByMa(nhaCungCap)
WebUI.delay(2)
page.clickSave()
WebUI.delay(1)
page.searchProductByMa(maCT)
WebUI.delay(2)
page.verifyNCC(nhaCungCap)
WebUI.comment(" Update thành công nhà cung cấp")