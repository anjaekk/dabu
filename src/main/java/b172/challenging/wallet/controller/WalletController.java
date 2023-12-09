package b172.challenging.wallet.controller;

import b172.challenging.wallet.dto.MaterialWalletResponseDto;
import b172.challenging.wallet.dto.WalletResponseDto;
import b172.challenging.wallet.service.WalletService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "함께 모으기 API", description = "함께 모으기 (홈 화면) API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/wallet")
public class WalletController {
    private final WalletService walletService;

    @GetMapping("/{userId}")
    @Parameter(name = "userId", description = "User Id", example = "user")
    public ResponseEntity<WalletResponseDto> getMyWallet (@PathVariable Long userId){
        return ResponseEntity.ok(walletService.findMyWallet(userId));
    }

    @GetMapping("/material/{userId}")
    @Parameter(name = "userId", description = "User Id", example = "user")
    public ResponseEntity<List<MaterialWalletResponseDto>> getMyMaterialWallet (@PathVariable Long userId){
        return ResponseEntity.ok(walletService.findMyMaterialWallet(userId));
    }
}
