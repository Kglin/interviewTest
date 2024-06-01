package net.interview.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.interview.test.pojo.JsonResult;
import net.interview.test.pojo.PurchaseOrder;
import net.interview.test.pojo.PurchaseReceipt;
import net.interview.test.service.ShoppingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Kglin
 * @version 1.0
 * @since 2024/6/1
 */

@Api("INTERVIEW")
@RestController
@RequestMapping("/interview")
public class ShoppingController {
    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping("/checkout")
    @ApiOperation(value = "checkout", notes = "checkout")
    public ResponseEntity<JsonResult<PurchaseReceipt>> checkout(
            @RequestBody
            PurchaseOrder order
    ) {
        return ResponseEntity.ok(new JsonResult<>(shoppingService.checkout(order)));
    }
}
