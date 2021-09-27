package me.study.lab.cotroller.get

import me.study.lab.model.dto.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping("/hello")
    fun hello() = "hello kotlin"

    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
        println("name = $name")
        println("age = $age")
        return "name = $name age = $age"
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}")
    fun pathVariable2(@PathVariable("name") _name: String, @PathVariable("age") _age: Int): String {
        println("name = $_name")
        println("age = $_age")
        return "name = $_name age = $_age"
    }

    @GetMapping("/get-mapping/query-param")
    fun queryParam(
            @RequestParam name: String,
            @RequestParam("age") age: Int
    ): String {
        println("name = $name")
        println("age = $age")
        return "name = $name, age = $age"
    }

    // name, age, address, email
    // 단 코틀린은 - 하이픈을 파싱이 안 되므로 이 같은 경우 따로 매개변수로 받아야한다.(사실 설계를 카멜케이스로 하면 된다.)
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println("userRequest = $userRequest")
        return userRequest
    }

    // map 방식은 객체 파싱이 아닌 컬렉션으로 만드는 것이므로 phone-number 와 같은 하이픈 네임도 적용된다.
    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println("javaClass = $map")
        val phoneNumber = map.get("phone-number")
        println("javaClass = $phoneNumber")
        return map
    }

}