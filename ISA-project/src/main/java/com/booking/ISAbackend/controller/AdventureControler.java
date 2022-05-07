package com.booking.ISAbackend.controller;

import com.booking.ISAbackend.dto.*;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.Cottage;
import com.booking.ISAbackend.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adventure")
public class AdventureControler {

    @Autowired
    private AdventureService adventureService;
//
//
//    @GetMapping("getTest")
//    @Transactional
//    public ResponseEntity<TestDTO> test(@RequestParam String id) throws IOException {
//        Adventure a = adventureService.findAdventureByI(Integer.parseInt(id));
//        List<byte[]> photos = new ArrayList<byte[]>();
//        for (Photo p : a.getPhotos()){
//            String folder = "./src/main/frontend/src/components/images/";
//            Path path = Paths.get(folder + p.getPath());
//            BufferedImage image = ImageIO.read(new FileInputStream(path.toString()));
//            //BufferedImage  Convert to  ByteArrayOutputStream
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            ImageIO.write(image, "jpg", out);
//            //ByteArrayOutputStream  Convert to  byte[]
//            byte[] imageByte = out.toByteArray();
//            MultipartFile pf = new ConvertToMultipartFile(imageByte, p.getPath(), p.getPath(), "jpg", imageByte.length );
//
//            photos.add(imageByte);
//        }
//        TestDTO dto = new TestDTO("photos", photos);
//
//        return ResponseEntity.ok(dto);
//    }

    @PostMapping(value = "addAdventure" )
    public ResponseEntity<String> addAdventure(@RequestParam("email") String ownerEmail,
                                               @RequestParam(value = "photos", required = false) List<MultipartFile> photos,
                                               @RequestParam("offerName") String offerName,
                                               @RequestParam("price") String price,
                                               @RequestParam("description") String description,
                                               @RequestParam("street") String street,
                                               @RequestParam("city") String city,
                                               @RequestParam("state") String state,
                                               @RequestParam("rulesOfConduct") String rulesOfConduct,
                                               @RequestParam("cancelationConditions") String cancelationConditions,
                                               @RequestParam("additionalEquipment") String additionalEquipment,
                                               @RequestParam("peopleNum") String peopleNum){
        //provera da li je ulogovan i autorizacija
        try {
            NewAdventureDTO adventureDTO = new NewAdventureDTO(ownerEmail, offerName, description, price, photos,
                    peopleNum, rulesOfConduct, cancelationConditions,
                    street, city, state, additionalEquipment);

            int adventureId = adventureService.addAdventure(adventureDTO);
            return ResponseEntity.ok(String.valueOf(adventureId));

        }
        catch (InvalidPriceException | AdventureAlreadyExistsException |InvalidPeopleNumberException | RequiredFiledException | InvalidAddressException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("add-additional-services")
    //RequestParam("additionalServices") List<AdditionalServiceDTO> additionalServiceDTOS,
    //                                                                   @RequestParam("offerId") String offerId
    public ResponseEntity<String> addAdditionalServiceForAdventure(@RequestBody Map<String, Object> data){
        try{


            HashMap<String, Object>paramsMap =  (HashMap<String, Object>) data.get("params");
            int id = Integer.parseInt(paramsMap.get("offerId").toString());
            List<HashMap<String, String>> additionalServiceDTOS = (List<HashMap<String, String>>) paramsMap.get("additionalServiceDTOS");

            adventureService.addAdditionalServices(additionalServiceDTOS, id);
            return ResponseEntity.ok().body("Successfully added new adventure");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("instructor-adventures")
    public ResponseEntity<List<AdventureDTO>> getInstructorAdventures(@RequestParam String email){
        try{
            List<AdventureDTO> adventures = adventureService.getInstructorAdventures(email);
            return ResponseEntity.ok(adventures);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("details")
    public ResponseEntity<AdventureDetailsDTO> getAdventureDetail(@RequestParam String id){
        try{
            AdventureDetailsDTO adventure = adventureService.findAdventureById(Integer.parseInt(id));
            return ResponseEntity.ok(adventure);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("update-adventure")
    public ResponseEntity<String> changeAdventureData(@RequestBody AdventureDTO newAdventureData){
         try{
             adventureService.updateAdventure(newAdventureData, newAdventureData.getId());
             return ResponseEntity.ok().body("");
         }catch (Exception e){
             e.printStackTrace();
             return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
         }
    }

    @PostMapping("update-adventure-services")
    public ResponseEntity<String> changeAdventrueAdditionalServices(@RequestBody Map<String, Object> data){
        try{
            HashMap<String, Object>paramsMap =  (HashMap<String, Object>) data.get("params");
            int id = Integer.parseInt(paramsMap.get("offerId").toString());
            List<HashMap<String, String>> additionalServiceDTOS = (List<HashMap<String, String>>) paramsMap.get("additionalServiceDTOS");

            adventureService.updateAdventureAdditionalServices(additionalServiceDTOS, id);
            return ResponseEntity.ok().body("Successfully change adventure");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("search-adventures")
    @Transactional
    public ResponseEntity<List<AdventureDTO>> searchAdventures(@RequestParam String name, @RequestParam String address, @RequestParam Integer maxPeople, @RequestParam Double price, @RequestParam String email){

        try{
            System.out.println(name + " " + address + " " + maxPeople + " " + price + " " + email);
            List<AdventureDTO> advetures = adventureService.searchAdventuresByInstructor(name, maxPeople, address, price, email);
            return ResponseEntity.ok(advetures);
        }catch  (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("update-allowed")
    public ResponseEntity<Boolean> isAllowedAdventureUpdate(@RequestParam String email, @RequestParam int adventureId){
        try{
            Boolean allowedUpdate = adventureService.chechUpdateAllowed(adventureId);
            return ResponseEntity.ok(allowedUpdate);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
