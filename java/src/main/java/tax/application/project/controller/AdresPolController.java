package tax.application.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tax.application.project.model.AdresPol;
import tax.application.project.repository.AdresPolRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AdresPolController {

    @Autowired
    private AdresPolRepository adresPolRepository;

    @GetMapping("/adresypol")
    public List<AdresPol> getAllAdresyPol() {
        return adresPolRepository.findAll();
    }

    @GetMapping("/adresypol/{id}")
    public ResponseEntity<AdresPol> getAdresPolById(@PathVariable(value = "id") Long adresPolId) {
        AdresPol adresPol = adresPolRepository.findById(adresPolId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono adresu o podanym id :: " + adresPolId));
        return ResponseEntity.ok().body(adresPol);
    }

    @PostMapping("/adresypol")
    public AdresPol createAdresPol(@Valid @RequestBody AdresPol adresPol) {
        return adresPolRepository.save(adresPol);
    }

    @PutMapping("/adresypol/{id}")
    public ResponseEntity <AdresPol> updateAdresPol(@PathVariable(value = "id") Long adresPolId,
                                                               @Valid @RequestBody AdresPol adresPolDetails) {
        AdresPol adresPol = adresPolRepository.findById(adresPolId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono adresu o podanym id :: " + adresPolId));

        adresPol.setKodKraju(adresPolDetails.getKodKraju());
        adresPol.setWojewodztwo(adresPolDetails.getWojewodztwo());
        adresPol.setPowiat(adresPolDetails.getPowiat());
        adresPol.setGmina(adresPolDetails.getGmina());
        adresPol.setUlica(adresPolDetails.getUlica());
        adresPol.setNrDomu(adresPolDetails.getNrDomu());
        adresPol.setNrLokalu(adresPolDetails.getNrLokalu());
        adresPol.setMiejscowosc(adresPolDetails.getMiejscowosc());
        adresPol.setKodKraju(adresPolDetails.getKodPocztowy());
        final AdresPol updatedAdresPol = adresPolRepository.save(adresPol);
        return ResponseEntity.ok(updatedAdresPol);
    }

    @DeleteMapping("/adresypol/{id}")
    public Map< String, Boolean > deleteAdresPol(@PathVariable(value = "id") Long adresPolId) {
        AdresPol adresPol = adresPolRepository.findById(adresPolId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono adresu o podanym id :: " + adresPolId));

        adresPolRepository.delete(adresPol);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
