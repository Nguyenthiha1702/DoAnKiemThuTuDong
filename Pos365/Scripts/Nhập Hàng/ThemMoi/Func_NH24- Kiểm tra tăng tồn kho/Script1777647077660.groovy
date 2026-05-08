import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.pages.ProductPage
import com.pages.PurchasePage

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase("common/login"), [:])

String ma = "add01205622"
int soLuong = 10
String giaBan="1000"
ProductPage productPage= new ProductPage()
PurchasePage purchasePage = new PurchasePage()

productPage.navigateToList()
productPage.searchProductByMa(ma)
int tonBefore = productPage.getTonKho()
purchasePage.moManHinhThemHang()
purchasePage.themSanPhamDayDu(ma, String.valueOf(soLuong),giaBan)
purchasePage.clickDone()
WebUI.click(findTestObject('Nhập Hàng/ThemMoi/but_DongYHoanThanh'))
productPage.navigateToList()
productPage.searchProductByMa(ma)
int tonAfter = productPage.getTonKho()
assert tonAfter == tonBefore + soLuong :
	"❌ Sai tồn kho | Before: ${tonBefore} | After: ${tonAfter} | Sold: ${soLuong}"