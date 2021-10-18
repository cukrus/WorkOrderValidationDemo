package net.cukrus.woValidationDemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.cukrus.woValidationDemo.model.dto.Analysis;
import net.cukrus.woValidationDemo.model.dto.Part;
import net.cukrus.woValidationDemo.model.dto.Repair;
import net.cukrus.woValidationDemo.model.dto.Replacement;
import net.cukrus.woValidationDemo.model.dto.WorkOrder;
import net.cukrus.woValidationDemo.util.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class JacksonConversionTests {
	private static final String PROVIDED_ANALYSIS_JSON = "{\"type\":\"ANALYSIS\",\"department\":\"GOoD analysis department\",\"start_date\":\"2020-08-13\",\"end_date\":\"2020-08-15\",\"currency\":\"USD\",\"cost\":123.12,\"parts\":[{\"inventory_number\":\"InventoryNumber1\",\"name\":\"PartNumber1\",\"count\":1},{\"inventory_number\":\"InventoryNumber2\",\"name\":\"PartNumber2\",\"count\":2}]}";
	private static final String PROVIDED_REPAIR_JSON = "{\"type\":\"REPAIR\",\"department\":\"GOoD repair department\",\"start_date\":\"2020-08-13\",\"end_date\":\"2020-08-16\",\"analysis_date\":\"2020-08-14\",\"test_date\":\"2020-08-15\",\"responsible_person\":\"GOoD repair master\",\"currency\":\"USD\",\"cost\":123.12,\"parts\":[{\"inventory_number\":\"InventoryNumber3\",\"name\":\"PartNumber3\",\"count\":3},{\"inventory_number\":\"InventoryNumber4\",\"name\":\"PartNumber4\",\"count\":4}]}";
	private static final String PROVIDED_REPLACEMENT_JSON = "{\"type\":\"REPLACEMENT\",\"department\":\"GOoD replacement department\",\"start_date\":\"2020-08-13\",\"end_date\":\"2020-08-16\",\"factory_name\":\"GOoD factory\",\"factory_order_number\":\"DE12345678\",\"currency\":\"USD\",\"cost\":123.12,\"parts\":[{\"inventory_number\":\"InventoryNumber5\",\"name\":\"PartNumber5\",\"count\":5},{\"inventory_number\":\"InventoryNumber6\",\"name\":\"PartNumber6\",\"count\":6}]}";
	private ObjectMapper jackson = new ObjectMapper();

	@Test
	void deserializationTest_analysisProvided() {
		Exception exception = null;
		WorkOrder workOrder = null;
		try {
			workOrder = jackson.readValue(PROVIDED_ANALYSIS_JSON, WorkOrder.class);
		} catch (JsonProcessingException e) {
			exception = e;
		}
		assertNull(exception, "should be no exceptions during deserialization");
		assertTrue(workOrder != null && workOrder instanceof Analysis, "should deserialize Analysis WorkOrder");
		assertWorkOrderFieldsNotNul(workOrder);
	}

	@Test
	void serializationTest_analysisProvided() {
		Exception exception = null;
		Analysis workOrder = new Analysis();
		fillDefaultWorkOrderFields(workOrder, "GOoD analysis department", "2020-08-15");
		workOrder.setParts(new ArrayList<>());
		workOrder.getParts().add(createPart(1));
		workOrder.getParts().add(createPart(2));
		String serializedWO = null;
		try {
			serializedWO = jackson.writeValueAsString(workOrder);
		} catch (JsonProcessingException e) {
			exception = e;
		}
		assertNull(exception, "should be no exceptions during serialization");
		assertTrue(serializedWO != null && !serializedWO.isBlank(), "serialized Analysis shouldn't be blank");
		assertEquals(PROVIDED_ANALYSIS_JSON, serializedWO, "serialized Analysis JSON should be equal to provided");
	}

	@Test
	void deserializationTest_repairProvided() {
		Exception exception = null;
		WorkOrder workOrder = null;
		try {
			workOrder = jackson.readValue(PROVIDED_REPAIR_JSON, WorkOrder.class);
		} catch (JsonProcessingException e) {
			exception = e;
		}
		assertNull(exception, "should be no exceptions during deserialization");
		assertTrue(workOrder != null && workOrder instanceof Repair, "should deserialize Repair WorkOrder");
		assertWorkOrderFieldsNotNul(workOrder);
	}

	@Test
	void serializationTest_repairProvided() {
		Exception exception = null;
		Repair workOrder = new Repair();
		fillDefaultWorkOrderFields(workOrder, "GOoD repair department", "2020-08-16");
		workOrder.setParts(new ArrayList<>());
		workOrder.getParts().add(createPart(3));
		workOrder.getParts().add(createPart(4));
		workOrder.setAnalysisDate(DateUtils.dateFromString("2020-08-14"));
		workOrder.setTestDate(DateUtils.dateFromString("2020-08-15"));
		workOrder.setResponsiblePerson("GOoD repair master");
		String serializedWO = null;
		try {
			serializedWO = jackson.writeValueAsString(workOrder);
		} catch (JsonProcessingException e) {
			exception = e;
		}
		assertNull(exception, "should be no exceptions during serialization");
		assertTrue(serializedWO != null && !serializedWO.isBlank(), "serialized Analysis shouldn't be blank");
		assertEquals(PROVIDED_REPAIR_JSON, serializedWO, "serialized Repair JSON should be equal to provided");
	}

	@Test
	void deserializationTest_replacementProvided() {
		Exception exception = null;
		WorkOrder workOrder = null;
		try {
			workOrder = jackson.readValue(PROVIDED_REPLACEMENT_JSON, WorkOrder.class);
		} catch (JsonProcessingException e) {
			exception = e;
		}
		assertNull(exception, "should be no exceptions during deserialization");
		assertTrue(workOrder != null && workOrder instanceof Replacement, "should deserialize Replacement WorkOrder");
		assertWorkOrderFieldsNotNul(workOrder);
	}

	@Test
	void serializationTest_replacementProvided() {
		Exception exception = null;
		Replacement workOrder = new Replacement();
		fillDefaultWorkOrderFields(workOrder, "GOoD replacement department", "2020-08-16");
		workOrder.setParts(new ArrayList<>());
		workOrder.getParts().add(createPart(5));
		workOrder.getParts().add(createPart(6));
		workOrder.setFactoryName("GOoD factory");
		workOrder.setFactoryOrderNumber("DE12345678");
		String serializedWO = null;
		try {
			serializedWO = jackson.writeValueAsString(workOrder);
		} catch (JsonProcessingException e) {
			exception = e;
		}
		assertNull(exception, "should be no exceptions during serialization");
		assertTrue(serializedWO != null && !serializedWO.isBlank(), "serialized Analysis shouldn't be blank");
		assertEquals(PROVIDED_REPLACEMENT_JSON, serializedWO, "serialized Replacement JSON should be equal to provided");
	}

	private void fillDefaultWorkOrderFields(WorkOrder workOrder, String department, String endDate) {
		workOrder.setDepartment(department);
		workOrder.setStartDate(DateUtils.dateFromString("2020-08-13"));
		workOrder.setEndDate(DateUtils.dateFromString(endDate));
		workOrder.setCurrency(Currency.getInstance("USD"));
		workOrder.setCost(123.12);
	}

	private Part createPart(int partNr) {
		return new Part("PartNumber" + partNr, "InventoryNumber" + partNr, partNr);
	}

	private void assertWorkOrderFieldsNotNul(WorkOrder workOrder) {
		assertNotNull(workOrder.getDepartment(), "department is null");
		assertNotNull(workOrder.getStartDate(), "startDate is null");
		assertNotNull(workOrder.getEndDate(), "endDate is null");
		assertNotNull(workOrder.getCost(), "cost is null");
		assertNotNull(workOrder.getCurrency(), "currency is null");
		assertTrue(!CollectionUtils.isEmpty(workOrder.getParts()), "parts are empty");

		if(workOrder instanceof Repair) {
			Repair repair = (Repair) workOrder;
			assertNotNull(repair.getAnalysisDate(), "analysisDate is null");
			assertNotNull(repair.getTestDate(), "testDate is null");
			assertNotNull(repair.getResponsiblePerson(), "responsiblePerson is null");
		} else if(workOrder instanceof Replacement) {
			Replacement replacement = (Replacement) workOrder;
			assertNotNull(replacement.getFactoryName(), "factoryName is null");
			assertNotNull(replacement.getFactoryOrderNumber(), "factoryOrderNumber is null");
		}
	}
}
