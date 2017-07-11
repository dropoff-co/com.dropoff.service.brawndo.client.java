import com.dropoff.service.brawndo.client.java.api.beans._
import com.dropoff.service.brawndo.client.java.api.ApiV1
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


/**
  * Created by jasonkastner on 7/3/17.
  */
object DropoffApp {
  def main(args: Array[String]): Unit = {
    System.out.println("HelloWorld!")
    val brawndo = new ApiV1
    //val url = "http://localhost:9094/v1"
    val url = "https://sandbow-brawndo.dropoff.com/v1"
    //val host = "localhost:9094"
    val host = "sandbox-brawndo.dropoff.com"
    val private_key = ""
    val public_key = ""
    brawndo.initialize(url, host, private_key, public_key)
    System.out.println("------------------------------")
    System.out.println("Getting API Info")
    val info = brawndo.info
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    if (info != null) System.out.println("Info: " + info.toString)
    else System.out.println("Info: NULL")
    var orderGetParams = new OrderGetParameters
    System.out.println("------------------------------")
    System.out.println("Getting Order Page")
    var page = brawndo.order.get(orderGetParams)
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("OrderPage: " + page.toString)
    val page1LastKey = page.get("last_key").getAsString
    if (page.get("last_key") != null) orderGetParams.setLastKey(page.get("last_key").getAsString)
    System.out.println("------------------------------")
    System.out.println("Getting Order Page 2")
    page = brawndo.order.get(orderGetParams)
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("Order Page 2: " + page.toString)
    val page2LastKey = page.get("last_key").getAsString
    System.out.println("page1LastKey: " + page1LastKey)
    System.out.println("page2LastKey: " + page2LastKey)
    System.out.println("last keys are equal? " + (page1LastKey eq page2LastKey))
    val order = page.get("data").getAsJsonArray.get(0)
    val order_id = order.getAsJsonObject.get("details").getAsJsonObject.get("order_id").getAsString
    System.out.println("------------------------------")
    System.out.println("Getting order_id: " + order_id)
    orderGetParams = new OrderGetParameters
    orderGetParams.setOrderId(order_id)
    val anOrder = brawndo.order.get(orderGetParams)
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("An order: " + anOrder.toString)
    System.out.println("------------------------------")
    System.out.println("Getting Order Estimate")
    val estimateParams = new EstimateParameters
    estimateParams.setOrigin("117 San Jacinto Blvd, Austin, TX 78701")
    estimateParams.setDestination("901 S MoPac Expy, Austin, TX 78746")
    val sdf = new SimpleDateFormat("zzz")
    estimateParams.setUtcOffset(sdf.format(new Date))
    var estimate = brawndo.order.estimate(estimateParams)
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("Estimate: " + estimate.toString)
    System.out.println("------------------------------")
    System.out.println("Getting Order Estimate 2")
    val tomorrowTenAM = Calendar.getInstance
    tomorrowTenAM.setTime(new Date)
    tomorrowTenAM.set(Calendar.HOUR_OF_DAY, 0)
    tomorrowTenAM.set(Calendar.MINUTE, 0)
    tomorrowTenAM.set(Calendar.SECOND, 0)
    tomorrowTenAM.add(Calendar.DATE, 1)
    tomorrowTenAM.add(Calendar.HOUR, 10)
    estimateParams.setUtcOffset(sdf.format(tomorrowTenAM.getTime))
    val tomorrowTenAMSeconds = tomorrowTenAM.getTimeInMillis / 1000
    estimateParams.setReadyTimestamp(tomorrowTenAMSeconds)
    estimate = brawndo.order.estimate(estimateParams)
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("Estimate 2: " + estimate.toString)
    System.out.println("------------------------------")
    System.out.println("Creating Order")
    val orderCreateParams = new OrderCreateParameters
    //orderCreateParams.setCompany_id("3e8e7d4a596ae41448d7e9c55a3a79bc");
    val originParams = new OrderCreateAddress
    originParams.setCompanyName("Gus's Fried Chicken")
    originParams.setFirstName("Napoleon")
    originParams.setLastName("Bonner")
    originParams.setAddressLine1("117 San Jacinto Blvd")
    //originParams.setAddress_line_2("");
    originParams.setCity("Austin")
    originParams.setState("TX")
    originParams.setZip("78701")
    originParams.setPhone("5125555555")
    originParams.setEmail("cluckcluck@gusfriedchicken.com")
    originParams.setLat(30.263706)
    originParams.setLng(-97.741703)
    originParams.setRemarks("Origin Remarks")
    orderCreateParams.setOrigin(originParams)
    val destinationParams = new OrderCreateAddress
    destinationParams.setCompanyName("Dropoff")
    destinationParams.setFirstName("Jason")
    destinationParams.setLastName("Kastner")
    destinationParams.setAddressLine1("901 S MoPac Expy")
    destinationParams.setAddressLine2("#150")
    destinationParams.setCity("Austin")
    destinationParams.setState("TX")
    destinationParams.setZip("78736")
    destinationParams.setPhone("512-555-5555")
    destinationParams.setEmail("jkastner+java+dropoff@dropoff.com")
    destinationParams.setLat(30.264573)
    destinationParams.setLng(-97.782073)
    destinationParams.setRemarks("Please use the front entrance. The back on is guarded by cats!")
    orderCreateParams.setDestination(destinationParams)
    val details = new OrderCreateDetails
    details.setReadyDate(tomorrowTenAMSeconds)
    details.setType("two_hr")
    details.setQuantity(10)
    details.setWeight(20)
    details.setDistance(estimate.get("data").getAsJsonObject.get("Distance").getAsString)
    details.setEta(estimate.get("data").getAsJsonObject.get("ETA").getAsString)
    details.setPrice(estimate.get("data").getAsJsonObject.get("two_hr").getAsJsonObject.get("Price").getAsString)
    //details.setReference_code("");
    //details.setReference_name("");
    orderCreateParams.setDetails(details)
    val createResponse = brawndo.order.create(orderCreateParams)
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("Create Order: " + createResponse.toString)
    val created_order_id = createResponse.get("data").getAsJsonObject.get("order_id").getAsString
    System.out.println("------------------------------")
    System.out.println("Adding Tip")
    val tipParams = new TipParameters
    tipParams.setOrderId(created_order_id)
    tipParams.setAmount(4.44)
    var tipResponse = brawndo.order.tip.create(tipParams)
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("Created Tip: " + tipResponse.toString)
    System.out.println("------------------------------")
    System.out.println("Getting Tip")
    tipResponse = brawndo.order.tip.get(tipParams)
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("Got Tip: " + tipResponse.toString)
    System.out.println("------------------------------")
    System.out.println("Deleting Tip")
    tipResponse = brawndo.order.tip.delete(tipParams)
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("Deleted Tip: " + tipResponse.toString)
    System.out.println("------------------------------")
    System.out.println("Cancelling Order")
    val cancelParams = new OrderCancelParameters
    cancelParams.setOrderId(created_order_id)
    val cancelResponse = brawndo.order.cancel(cancelParams)
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("++++++++++++++++++++++++++++++")
    System.out.println("Cancelled Order: " + cancelResponse.toString)
    //brawndo.order.simulate("austin");
    brawndo.shutdown()
  }
}
