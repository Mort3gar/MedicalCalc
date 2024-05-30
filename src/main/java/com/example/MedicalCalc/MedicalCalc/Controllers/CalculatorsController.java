package com.example.MedicalCalc.MedicalCalc.Controllers;

import com.example.MedicalCalc.MedicalCalc.DataObjects.requests.AvgBloodPressureRequest;
import com.example.MedicalCalc.MedicalCalc.DataObjects.requests.TitrationRequest;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.MedicalCalc.MedicalCalc.DataObjects.requests.BMIRequest;
import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorFullInfo;
import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorInfo;
import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorResult;
import com.example.MedicalCalc.MedicalCalc.Exceptions.API.Message;
import com.example.MedicalCalc.MedicalCalc.Exceptions.Validation.ValidationErrorResponse;
import com.example.MedicalCalc.MedicalCalc.Services.CalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Tag(
        name = "Контроллер API калькуляторов",
        description =
                """
                        Позволяет получить информацию о медицинских калькуляторах, а также результаты 
                        вычисления на основе принимаемых параметров
                        """
)
@RestController
@RequestMapping("/calculator")
public class CalculatorsController {
    private final CalculatorService calculatorService;

    public CalculatorsController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
        log.info("Создан контроллер калькулятора");
    }

    @Operation(
            summary = "Получить калькулятор по его идентификатору",
            description =
                    """
                    Позволяет получить полную информацию о калькуляторе по его идентификатору "id". 
                    Информация включает: "id" - идентификатор калькулятора, "name" - 
                    название калькулятора и "info" - описание калькулятора"
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Калькулятор был получен по его идентификатору id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CalculatorFullInfo.class)
                    )}
            ),
            @ApiResponse(responseCode = "404",
                    description = "Калькулятор не был найден по его идентификатору id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Message.class),
                            examples = @ExampleObject(value =
                                    """
                                            {
                                              "statusCode": 404,
                                              "timestamp": "27-05-2024 00:00:00",
                                              "message": "Калькулятор с идентификатором id не найден",
                                              "description": "uri=/calculator/id"
                                            }
                                            """
                            )
                    )}
            )
    })
    @GetMapping("/{id}")
    @ResponseBody
    public CalculatorFullInfo get(@PathVariable(name = "id")
                                  @Parameter(description = "Идентификатор калькулятора") Long id) {
        log.info("Запрос GET по адресу: /calculator/" + id);
        return calculatorService.getOne(id);
    }

    @Operation(
            summary = "Получить список доступных калькуляторов",
            description =
                    """
                            Позволяет получить полную информацию о все калькуляторах, которые можно
                            использовать. Информация включает список из: "id" - идентификатор калькулятора,
                            "name" - название калькулятора и "info" - описание калькулятора
                            """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Калькуляторы были получены",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CalculatorFullInfo.class))
                    )}
            )
    })
    @GetMapping
    @ResponseBody
    public List<CalculatorFullInfo> getAll() {
        log.info("Запрос GET по адресу: /calculator");
        return calculatorService.getAll();
    }

    @Operation(
            summary = "Получение информацию о калькуляторе по его названию",
            description =
                    """
                            Позволяет получить описание калькулятора по его названию "name". 
                            Описание включает: "info" - описание калькулятора
                            """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Был получен калькулятор по его названию name",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CalculatorInfo.class)
                    )}
            ),
            @ApiResponse(responseCode = "404",
                    description = "Калькулятор не был найден по его названию name",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Message.class),
                            examples = @ExampleObject(value =
                                    """
                                            {
                                              "statusCode": 404,
                                              "timestamp": "27-05-2024 00:00:00",
                                              "message": "Калькулятор с названием name не был найден",
                                              "description": "uri=/calculator/name/info"
                                            }
                                            """
                            )
                    )}
            )
    })
    @GetMapping("/{name}/info")
    @ResponseBody
    public CalculatorInfo getInfo(@PathVariable(name = "name")
                                  @Parameter(description = "Название калькулятора") String name) {
        log.info("Запрос GET по адресу: /calculator/" + name + "/info");
        return calculatorService.getInfo(name);
    }

    @Operation(
            summary = "Получение результата вычисления для калькулятора индекса массы тела",
            description =
                """
                Позволяет получить результат вычисления для калькулятора индекса массы тела. 
                Результат включает: "result" - результат вычисления в кг/м²
                """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Был получен результат вычисления калькулятора индекса массы тела",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CalculatorResult.class),
                            examples = @ExampleObject(value = "{\"result\": 20.194}"
                            )
                    )}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Неверно заданы параметры",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorResponse.class),
                            examples = @ExampleObject(value =

                                """
                                {
                                  "statusCode": 400,
                                  "timestamp": "27-05-2024 00:00:00",
                                  "violations": [
                                    {
                                      "fieldName": "Минимальный вес составляет 10 кг",
                                      "message": "getBMIResult.request.weight"
                                    },
                                    {
                                      "fieldName": "Рост не должен превышать 300 см",
                                      "message": "getBMIResult.request.height"
                                    }
                                  ]
                                }
                                """
                            )
                    )}
            )
    })
    @PostMapping("/body-mass-index/result")
    @ResponseBody
    public CalculatorResult BMIResult(@RequestBody BMIRequest request) {
        log.info("Запрос POST по адресу: /body-mass-index/result");
        return calculatorService.getBMIResult(request);
    }

    @Operation(
            summary =
                """
                Получение результата вычисления для калькулятора
                расчета среднего артериального давления по данным систолического и диастолического АД
                """,
            description =
                """
                Позволяет получить результат вычисления для калькулятора расчета
                среднего артериального давления по данным систолического и диастолического АД.
                Результат включает: "result" - результат вычисления в мм. рт. ст.
                """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description =
                        """
                        Был получен результат вычисления калькулятора расчета среднего
                        артериального давления по данным систолического и диастолического АД
                        """,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CalculatorResult.class),
                            examples = @ExampleObject(value = "{\"result\": 13}"
                            )
                    )}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Неверно заданы параметры",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorResponse.class),
                            examples = @ExampleObject(value =
                                """
                                {
                                   "statusCode": 400,
                                   "timestamp": "27-05-2024 00:00:00",
                                   "violations": [
                                     {
                                       "fieldName": "getTitrationResult.request.sad",
                                       "message": "Систолическое АД строго положительное"
                                     },
                                     {
                                       "fieldName": "getTitrationResult.request.dad",
                                       "message": "Значение Диастолическое АД является строго положительное"
                                     }
                                   ]
                                 }
                                """
                            )
                    )}
            )
    })
    @PostMapping("/average-blood-pressure/result")
    @ResponseBody
    public CalculatorResult ABPResult(@RequestBody AvgBloodPressureRequest request){
        log.info("Запрос POST по адресу: /average-blood-pressure/result");
        return calculatorService.getABPResult(request);
    }

    @Operation(
            summary =
                    """
                            Получение результата вычисления для калькулятора расчета скорости инфузии
                            препарата через линеомат(скорость титрования)
                            """,
            description =
                    """
                            Позволяет получить результат вычисления для калькулятора расчета скорости инфузии
                            препарата через линеомат(скорость титрования). Результат включает: "result" -
                            результат вычисления в мкг/кг*мин
                            """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description =
                            """
                                    Был получен результат вычисления калькулятора расчета скорости инфузии
                                    через ленеомат(скорость титрования)
                                    """,
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CalculatorResult.class),
                            examples = @ExampleObject(value = "{\"result\": 13.4}"
                            )
                    )}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Неверно заданы параметры",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorResponse.class),
                            examples = @ExampleObject(value =
                                """
                                {
                                   "statusCode": 400,
                                   "timestamp": "29-05-2024 00:00:00",
                                   "violations": [
                                     {
                                       "fieldName": "getABPResult.request.dosage",
                                       "message": "Дозировка препарата строго положительная"
                                     },
                                     {
                                       "fieldName": "getABPResult.request.amountOfDrug",
                                       "message": "Значение количества препарата является строго положительным"
                                     },
                                     {
                                       "fieldName": "getABPResult.request.weight",
                                       "message": "Значение веса строго положительное"
                                     },
                                     {
                                       "fieldName": "getABPResult.request.volumeOfSolution",
                                       "message": "Значение общего объема раствора является строго положительным"
                                     }
                                   ]
                                 }
                                """
                            )
                    )}
            )
    })
    @PostMapping("/titration-rate/result")
    @ResponseBody
    public CalculatorResult TitrationResult(@RequestBody TitrationRequest request) {
        log.info("Запрос POST по адресу: /titration-rate/result");
        return calculatorService.getTitrationResult(request);
    }

}