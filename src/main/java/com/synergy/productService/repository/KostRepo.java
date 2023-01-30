package com.synergy.productService.repository;

import com.synergy.productService.entity.Kost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Map;




@Repository
public interface KostRepo extends JpaRepository<Kost, Long> {

    @Query(value = "SELECT count(profile_id) FROM kost k WHERE k.profile_id = :profileId", nativeQuery = true)
    Integer checkExistingProfileId(Long profileId);

//    List<Kost> findByProfileId(Long profilepId);

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


    @Query(nativeQuery = true, value = "select k.kost_id, k.name, k.city, k.address, pr.price, pr.duration_type, r.kost_type_man,\n" +
            "r.kost_type_mixed , r.kost_type_woman  from kost k\t " +
            "\tleft join room r on k.kost_id = r.kost_id and r.deleted_at is null\n" +
            "\tleft join facility fa on fa.facility_id = r.facility_id and fa.deleted_at is null\n" +
            "\tleft join price pr on  pr.room_id = r.room_id and pr.deleted_at is null\n" +
            "\twhere k.deleted_at is null " +
            "\tand (( fa.ac = :ac) \n" +
            "\tor ( fa.blanket = :blanket ) \n" +
            "\tor ( fa.fan = :fan) \n" +
            "\tor ( fa.furniture = :furniture) \n" +
            "\tor (fa.shower = :shower) \n" +
            "\tor (fa.sitting_closet = :sitting_closet) \n" +
            "\tor (fa.springbed = :springbed) \n" +
            "\tor (fa.table_learning = :table_learning) \n" +
            "\tor (fa.water_heater = :water_heater) \n" +
            "\tor (fa.inside_bathroom = :inside_bathroom) \n" +
            "\tor (fa.non_sitting_closet = :non_sitting_closet) \n" +
            "\tor (fa.outside_bathroom = :outside_bathroom) \n" +
            "\tor (fa.windows = :windows) \n" +
            "\tor (fa.room_tv = :room_tv) \n" +
            "\tor (r.kost_type_man = :kost_type_man) \n" +
            "\tor (r.kost_type_woman = :kost_type_woman) \n" +
            "\tor (r.kost_type_mixed = :kost_type_mixed) \n" +
            "\tor (pr.duration_type = :duration_type) \n" +
            "\tor (pr.price > :price_minimum and pr.price < :price_maximum) \n" +
            "\tor (k.kost_tv = :kost_tv) \n" +
            "\tor (k.electric = :electric) \n" +
            "\tor (k.laundry = :laundry) \n" +
            "\tor (k.refrigerator = :refrigerator) \n" +
            "\tor (k.water = :water) \n" +
            "\tor (k.wifi = :wifi) \n" +
            "\tor (k.dispenser = :dispenser) \n" +
            "\tor (k.drying_ground = :drying_ground) \n" +
            "\tor (k.kitchen = :kitchen) \n" +
            "\tor (k.living_room = :living_room) \n" +
            "\tor (k.parking = :parking))")
    List<Map<String, Object>> getKostByFilterWithPagination(@Param("ac") Boolean ac,
                                             @Param("blanket") Boolean blanket,
                                             @Param("fan") Boolean fan,
                                             @Param("furniture") Boolean furniture,
                                             @Param("shower") Boolean shower,
                                             @Param("sitting_closet") Boolean sittingCloset,
                                             @Param("springbed") Boolean springbed,
                                             @Param("table_learning") Boolean table,
                                             @Param("water_heater") Boolean waterHeater,
                                             @Param("inside_bathroom") Boolean insideBathroom,
                                             @Param("non_sitting_closet") Boolean nonSittingCloset,
                                             @Param("outside_bathroom") Boolean outsideBathroom,
                                             @Param("windows") Boolean windows,
                                             @Param("room_tv") Boolean roomTv,
                                             @Param("kost_type_man") Boolean kostTypeMan,
                                             @Param("kost_type_woman") Boolean kostTypeWoman,
                                             @Param("kost_type_mixed") Boolean kostTypeMixed,
                                             @Param("duration_type") String durationType,
                                             @Param("price_minimum") Double priceMinimum,
                                             @Param("price_maximum") Double priceMaximum,
                                             @Param("kost_tv") Boolean kostTv,
                                             @Param("electric") Boolean electric,
                                             @Param("laundry") Boolean laundry,
                                             @Param("refrigerator") Boolean refrigerator,
                                             @Param("water") Boolean water,
                                             @Param("wifi") Boolean wifi,
                                             @Param("dispenser") Boolean dispenser,
                                             @Param("drying_ground") Boolean dryingGround,
                                             @Param("kitchen") Boolean kitchen,
                                             @Param("living_room") Boolean livingRoom,
                                             @Param("parking") Boolean parking, Pageable pageable);



    @Query(value = "select\n" +
            "\tk.kost_id,\n" +
            "\tk.\"name\",\n" +
            "\tk.address,\n" +
            "\tk.city,\n" +
            "\tp.price,\n" +
            "\tr.kost_type_man,\n" +
            "\tr.kost_type_mixed,\n" +
            "\tr.kost_type_woman,\n" +
            "\tp.duration_type\n" +
            "from\n" +
            "\tkost k\n" +
            "left join room r on\n" +
            "\tk.kost_id = r.kost_id\n" +
            "left join price p on\n" +
            "\tr.room_id = p.room_id\n" +
            "where\n" +
            "\tk.\"name\" ilike %:search% \n" +
            "\tor k.address ilike %:search% \n" +
            "\tor k.city ilike %:search% ", nativeQuery = true)
    List<Map<String, Object>> getKostBySearchWithPagination(@Param("search") String search,
                                               Pageable pageable);
}