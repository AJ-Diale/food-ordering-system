package foodorderingsystem.controller;

import foodorderingsystem.dto.MenuDto;
import foodorderingsystem.response.ApiResponse;
import foodorderingsystem.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<ApiResponse<MenuDto>> create(
            @RequestBody @Valid MenuDto dto) {

        return ResponseEntity.ok(
                menuService.createMenu(dto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MenuDto>>> all() {

        return ResponseEntity.ok(
                menuService.getAllMenus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuDto>> byId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                menuService.getMenuById(id));
    }
}