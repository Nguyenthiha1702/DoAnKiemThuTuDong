import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('common/login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Khách hàng/menu_Doitac'))

WebUI.click(findTestObject('Khách hàng/sub_KhachHang'))

WebUI.click(findTestObject('Khách hàng/thêm mới/but_ThemMoi'))

WebUI.setText(findTestObject('Khách hàng/thêm mới/txt_Ten'), 'hà')

WebUI.setText(findTestObject('Khách hàng/thêm mới/txt_CCCD'), '')

WebUI.setText(findTestObject('Khách hàng/thêm mới/num_Dienthoai1'), '')

WebUI.setText(findTestObject('Khách hàng/thêm mới/num_Dienthoai2'), '')

WebUI.setText(findTestObject('Khách hàng/thêm mới/txt_BoPhan'), '')

WebUI.setText(findTestObject('Khách hàng/thêm mới/txt_Congty'), '')

WebUI.uploadFile(findTestObject('Khách hàng/thêm mới/input_hinhanh'), 'C:\\Users\\PC\\Downloads\\áo.png')

WebUI.click(findTestObject('Khách hàng/thêm mới/cbobox_TinhThanh'))

WebUI.waitForElementVisible(findTestObject('Khách hàng/thêm mới/vd_Tp_HaNoi'), 5)

WebUI.click(findTestObject('Khách hàng/thêm mới/vd_Tp_HaNoi'))

WebUI.click(findTestObject('Khách hàng/thêm mới/but_Luu'))

