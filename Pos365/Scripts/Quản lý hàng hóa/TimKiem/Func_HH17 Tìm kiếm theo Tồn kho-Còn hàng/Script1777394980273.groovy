import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.pages.ProductPage

ProductPage productPage = new ProductPage()

// Đăng nhập
WebUI.callTestCase(findTestCase("common/login"), [:])

// Mở danh sách hàng hóa
productPage.navigateToList()

// Chọn lọc còn hàng
WebUI.click(findTestObject('Hàng hóa/TimKiem/cbox_TonKhoConHang'))

// Chờ grid reload
WebUI.delay(1)

// Lấy tồn kho dòng đầu tiên
String tonKhoText = WebUI.getText(
	findTestObject('Hàng hóa/XemChiTiet/row_TonKhoDauTien')
)

tonKhoText = tonKhoText.replace(",", "").trim()

println("Tồn kho thực tế: " + tonKhoText)

int tonKho = Integer.parseInt(tonKhoText)

// Verify còn hàng
assert tonKho > 0 :
	"Sản phẩm đầu tiên vẫn hết hàng. Tồn kho = ${tonKho}"

// Tìm mã hàng hết hàng
String ma1 = "HatestHetHang"

productPage.searchProductByMa(ma1)

// Chờ search
WebUI.delay(1)

// Kiểm tra row đầu tiên có tồn tại không
boolean isPresent = WebUI.verifyElementPresent(
	findTestObject('Hàng hóa/XemChiTiet/row_MaHangDauTien'),
	2,
	FailureHandling.OPTIONAL
)

// Verify không hiển thị sản phẩm hết hàng
assert !isPresent :
	"Vẫn hiển thị mã hàng hết hàng: ${ma1}"

WebUI.comment("Không hiển thị mã hàng hết hàng")