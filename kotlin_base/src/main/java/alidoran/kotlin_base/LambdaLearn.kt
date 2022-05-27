package ir.alidoran.teach_kotlin

object LambdaLearn {

    @JvmStatic
    fun main(args: Array<String>) {
        collectionWithLambda()
    }

    fun testListLambda() {
        var list = listOf(1, 2, 3)

        list.any({ i: Int -> i > 0 })
        list.any() { i: Int -> i > 0 }
        list.any { i: Int -> i > 0 }
        list.any { i -> i > 0 }
        list.any { it > 0 }



        list.any {
            println("Hi")
            it > 0
        }

        val mapList = mapOf(1 to "one", 2 to "two", 3 to "three")
        mapList.mapValues { entry -> "${entry.key} => ${entry.value}!" }
        mapList.mapValues { (key, value) -> "${key} => ${value}!" }
        mapList.mapValues { (_, value) -> "${value}!" }

    }

    enum class City {
        Tehran, Berlin
    }

    fun collectionWithLambda() {
        var simpleList: List<Int> = listOf(5, 2, 3, 4, 1, 9)

        val mapList = mapOf(1 to "one", 2 to "two", 3 to "three")

        data class Employee(
            val city: City, val age: Int
        )

        var iranEmployee1: Employee = Employee(city = City.Tehran, age = 35)
        var iranEmployee2: Employee = Employee(city = City.Tehran, age = 37)
        var germanEmployee1: Employee = Employee(city = City.Berlin, age = 36)
        var germanEmployee2: Employee = Employee(city = City.Berlin, age = 38)
        var germanEmployee3: Employee = Employee(city = City.Berlin, age = 37)

        val employees: List<Employee> =
            listOf(iranEmployee1, iranEmployee2, germanEmployee1, germanEmployee2, germanEmployee3)

        employees.filter { it.city == City.Tehran }.map { it.age }.average()

        employees.filter { it.city == City.Tehran } //result= {iranEmployee1, iranEmployee2}
        employees.map { it.age - 5 } //result = {30,32,31,33 ,32}
        employees.all { it.age > 34 } //result = true
        employees.all { it.age > 35 } //result = false
        employees.none { it.age < 35 } //result = true
        employees.none { it.age < 36 } //result = false
        employees.any { it.age == 34 } //result = False
        employees.any { it.age == 35 } //result = True
        employees.find { it.city == City.Tehran } // result = iranEmployee1
        employees.find { it.age == 34 } // result = null
        employees.first() //result = iranEmployee1 if result == null go to exception
        employees.firstOrNull() //result = iranEmployee1
        employees.last() //result = germanEmployee3 if result == null go to exception
        employees.lastOrNull() //result = germanEmployee3
        employees.count { 37 < it.age } //result = 1
        employees.partition { it.age < 37 } //result {{iranEmployee1 , germanEmployee1} , {iranEmployee2 , germanEmployee2 , germanEmployee3}}
        employees.groupBy { it.age } // result = {{35,iranEmployee1}{{37,{iranEmployee2,germanEmployee3}}}{36,germanEmployee1}{38,germanEmployee2}}
        employees.associateBy { it.age } //result = {{Tehran , iranEmployee2},{Berlin , germanEmployee3}} in this case only save last elements
        employees.associate { it.age to it } // result = {{35, iranEmployee1},{37 , germanEmployee3},{36, germanEmployee1},{38, germanEmployee2}}
        employees.zip(simpleList) //result = {{iranEmployee1,5},{iranEmployee2,2},{germanEmployee1,3},{germanEmployee2,4},{germanEmployee3,1}}
        employees.zipWithNext() //result = {{iranEmployee1, iranEmployee2},{iranEmployee2, iranEmployee3},{iranEmployee3,iranEmployee4},{iranEmployee4,iranEmployee5}}
        employees.map { it.age }.distinct() //result = {35,37,36,38}
        employees.maxByOrNull { it.age } //result = germanEmployee2
        employees.flatMap { employee1: Employee -> employees.map { employee2 -> employee1 to employee2 } } //result = Cartesian multiplication of employees
    }

    private fun objectLambda(inputInt: Int?) {
        inputInt?.let { i -> println(i) } //If inputInt != null
        val number = 5
        println(number.takeIf { it < 6 }) // 5
        println(number.takeIf { it > 6 }) // null
    }
}