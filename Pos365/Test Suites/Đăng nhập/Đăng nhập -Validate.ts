<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description></description>
   <name>Đăng nhập -Validate</name>
   <tag></tag>
   <isRerun>false</isRerun>
   <mailRecipient></mailRecipient>
   <numberOfRerun>0</numberOfRerun>
   <pageLoadTimeout>10</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>false</rerunFailedTestCasesOnly>
   <rerunImmediately>true</rerunImmediately>
   <testSuiteGuid>51a98220-2ddb-436f-b5ca-445da8522baa</testSuiteGuid>
   <testCaseLink>
      <guid>52d13223-a3d1-4f0f-b953-4c35fca55ae4</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/common/openBrower</testCaseId>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
   </testCaseLink>
   <testCaseLink>
      <guid>875b4e27-434a-4fa2-a732-032fe6fb51b9</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/Đăng nhập/Validation/Val_DN01--10- Validate Tên đăng nhập</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>bd93ff49-7afc-4553-baa9-af05e75157d6</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/Login/Validate/validateTenDangNhap</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>bd93ff49-7afc-4553-baa9-af05e75157d6</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Tên </value>
         <variableId>ca4cbcf0-84f1-4edd-ab99-5563650ed3b6</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>bd93ff49-7afc-4553-baa9-af05e75157d6</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>TC_id</value>
         <variableId>eabc671b-0c8c-4f8a-9119-fdb1dab85b18</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>bd93ff49-7afc-4553-baa9-af05e75157d6</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>expect</value>
         <variableId>00876f32-51bc-46e9-ac1a-7de1ee09e94c</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>3cf2483c-4cd1-4e37-b509-544be7384555</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/Đăng nhập/Validation/Val_DN011--19- Validate Mật khẩu</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>64c369a3-31ef-42ae-8483-6243a5788c0d</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/Login/Validate/ValidateMatKhau</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>64c369a3-31ef-42ae-8483-6243a5788c0d</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>Mật khẩu</value>
         <variableId>1dca9f0e-6def-408a-9485-5be59c9f2828</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>64c369a3-31ef-42ae-8483-6243a5788c0d</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>TC_id</value>
         <variableId>9234a0df-bce1-4940-bb60-4b6acbf5db12</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>64c369a3-31ef-42ae-8483-6243a5788c0d</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>expect</value>
         <variableId>fcd5b8ce-d2da-490b-a5c8-9cb9648b3dc9</variableId>
      </variableLink>
   </testCaseLink>
</TestSuiteEntity>
