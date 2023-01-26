package com.synergy.productService.repository;

import com.synergy.productService.entity.Kost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface KostRepo extends JpaRepository<Kost, Long> {
//    @Query(value = "SELECT k FROM Kost k WHERE k.id = :id", nativeQuery = false)
//    Kost checkExistingKostId(Long id);

    @Query(value = "SELECT * FROM kost k WHERE k.kost_id = :id AND k.enabled = true", nativeQuery = true)
    Kost checkExistingKostId(Long id);

    @Query(value = "SELECT * FROM kost k WHERE k.kost_id = :id", nativeQuery = true)
    Kost checkExistingKostIdAdmin(Long id);


    @Query(value = "select k from Kost k", nativeQuery = false)
    public Page<Kost> getListDataAdmin(Pageable pageable);

    @Query(value = "SELECT * FROM kost k WHERE k.profile_id = :profileId", nativeQuery = true)
    public Page<Kost> getListDataTennant(@Param("profileId") Long profileId, Pageable pageable);


    @Query(value = "\tselect ko.kost_id,ko.name, ko.front_building_photo, pr.price,\n" +
            "\t\tpr.duration_type, r.kost_type_man, r.kost_type_mixed,\n" +
            "\t\tr.kost_type_woman, ko.city, ko.province\t\t\n" +
            "from room r\n" +
            "\tjoin facility f on f.facility_id = r.facility_id\n" +
            "\t join price pr on  pr.price_id = r.price_id\n" +
            "\t join kost ko on ko.kost_id = r.kost_id\n" +
            "\twhere (:city is null or ko.city like %:city%)\n" +
            "\tand (:name is null or ko.name like '%:name:%') ", nativeQuery = true)
    List<Object> getKostBySearchWithPagination(@Param("city") String city,
                                               @Param("name") String name,
                                               Pageable pageable);


    @Query(nativeQuery = true, value = "select ko.kost_id, ko.name, ko.front_building_photo, pr.price,\n" +
            "\tpr.duration_type, r.kost_type_man, r.kost_type_mixed,\n" +
            "\tr.kost_type_woman, ko.city, ko.province \t " +
            "\tfrom room r\n" +
            "\tjoin facility f on f.facility_id = r.facility_id \n" +
            "\tjoin price pr on  pr.price_id = r.price_id \n" +
            "\tjoin kost ko on ko.kost_id = r.kost_id \n" +
            "\twhere (:ac is null or f.ac = :ac) \n" +
            "\tand (:blanket is null or f.blanket = :blanket ) \n" +
            "\tand (:fan is null or f.fan = :fan) \n" +
            "\tand (:furniture is null or f.furniture = :furniture) \n" +
            "\tand (:shower is null or f.shower = :shower) \n" +
            "\tand (:sitting_closet is null or f.sitting_closet = :sitting_closet) \n" +
            "\tand (:springbed is null or f.springbed = :springbed) \n" +
            "\tand (:table_learning is null or f.table_learning = :table_learning) \n" +
            "\tand (:water_heater is null or f.water_heater = :water_heater) \n" +
            "\tand (:inside_bathroom is null or f.inside_bathroom = :inside_bathroom) \n" +
            "\tand (:non_sitting_closet is null or f.non_sitting_closet = :non_sitting_closet) \n" +
            "\tand (:outside_bathroom is null or f.outside_bathroom = :outside_bathroom) \n" +
            "\tand (:kost_tv is null or f.tv = :kost_tv) \n" +
            "\tand (:kost_type_man is null or r.kost_type_man = :kost_type_man) \n" +
            "\tand (:kost_type_woman is null or r.kost_type_woman = :kost_type_woman) \n" +
            "\tand (:kost_type_mixed is null or r.kost_type_mixed = :kost_type_mixed) \n" +
            "\tand (:duration_type is null or pr.duration_type = :duration_type) \n" +
            "\tand (pr.price >= :price_minimum and pr.price <= :price_maximum) \n" +
            "\tand (:dispenser is null or ko.dispenser = :dispenser) \n" +
            "\tand (:electric is null or ko.electric = :electric) \n" +
            "\tand (:laundry is null or ko.laundry = :laundry) \n" +
            "\tand (:refrigerator is null or ko.refrigerator = :refrigerator) \n" +
            "\tand (:water is null or ko.water = :water) \n" +
            "\tand (:wifi is null or ko.wifi = :wifi) \n" +
            "\tand (:drying_ground is null or ko.drying_ground = :drying_ground) \n" +
            "\tand (:kitchen is null or ko.kitchen = :kitchen) \n" +
            "\tand (:living_room is null or ko.living_room = :living_room) \n" +
            "\tand (:parking is null or ko.parking = :parking) \n" +
            "\tand (:room_tv is null or ko.tv = :room_tv)")
    List<Object> getKostByFilterWithPagination(@Param("ac") Boolean ac, @Param("blanket") Boolean blanket,
                                               @Param("fan") Boolean fan, @Param("furniture") Boolean furniture,
                                               @Param("shower") Boolean shower, @Param("sitting_closet") Boolean sittingCloset,
                                               @Param("springbed") Boolean springbed, @Param("table_learning") Boolean table, @Param("water_heater") Boolean waterHeater,
                                               @Param("inside_bathroom") Boolean insideBathroom, @Param("non_sitting_closet") Boolean nonSittingCloset,
                                               @Param("outside_bathroom") Boolean outsideBathroom, @Param("kost_tv") Boolean kostTv,
                                               @Param("kost_type_man") Boolean kostTypeMan, @Param("kost_type_woman") Boolean kostTypeWoman, @Param("kost_type_mixed") Boolean kostTypeMixed,
                                               @Param("duration_type") String durationType,
                                               @Param("price_minimum") Double priceMinimum, @Param("price_maximum") Double priceMaximum,
                                               @Param("dispenser") Boolean dispenser, @Param("electric") Boolean electric,
                                               @Param("laundry") Boolean laundry, @Param("refrigerator") Boolean refrigerator, @Param("water") Boolean water,
                                               @Param("wifi") Boolean wifi, @Param("drying_ground") Boolean dryingGround,
                                               @Param("kitchen") Boolean kitchen,@Param("living_room") Boolean livingRoom,
                                               @Param("parking") Boolean parking,@Param("room_tv") Boolean roomTv, Pageable pageable);

}