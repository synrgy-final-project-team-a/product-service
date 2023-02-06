package com.synergy.productService.repository;

import com.synergy.productService.entity.Kost;
import com.synergy.productService.entity.Price;
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

    @Query(value = "SELECT * FROM kost k WHERE k.kost_id = :kost_id AND k.enabled = true", nativeQuery = true)
    Kost checkExistingKostId(@Param("kost_id") Long id);
    @Query(value = "SELECT * FROM kost k WHERE k.kost_id = :kost_id", nativeQuery = true)
    Kost checkExistingKostIdAdmin(@Param("kost_id") Long id);


    @Query(value = "select * from kost k where k.enabled = :enabled", nativeQuery = true)
    public Page<Kost> getListDataAdmin(@Param("enabled") Boolean enabled, Pageable pageable);

    @Query(value = "SELECT * FROM kost k WHERE k.profile_id = :profileId", nativeQuery = true)
    public Page<Kost> getListDataTennant(@Param("profileId") Long profileId, Pageable pageable);


    @Query(nativeQuery = true, value = "select k.kost_id, k.kost_name, k.city, k.address, k.province, pr.price, pr.duration_type, k.kost_type_man,\n" +
            "k.kost_type_mixed , k.kost_type_woman, k.front_building_photo  from kost k\t " +
            "\tleft join room r on k.kost_id = r.kost_id and r.deleted_at is null\n" +
            "\tleft join facility fa on fa.facility_id = r.facility_id and fa.deleted_at is null\n" +
            "\tleft join price pr on  pr.room_id = r.room_id and pr.deleted_at is null\n" +
            "\twhere k.deleted_at is null " +
            "\tand k.enabled = true \n" +
            "\tand (( fa.ac = :ac) \n" +
            "\tor ( fa.pillow = :pillow ) \n" +
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
            "\tor (k.parking_car = :parking_car) \n" +
            "\tor (k.parking_motorcycle = :parking_motorcycle))" +
            "\tand lower(k.province) like (%:province%) \n"  +
            "\tand lower(k.city)  like (%:city%)" +
            "\tand (k.kost_type_man = :kost_type_man) \n" +
            "\tand (k.kost_type_woman = :kost_type_woman) \n" +
            "\tand (k.kost_type_mixed = :kost_type_mixed) \n" +
            "\tand (pr.duration_type = :duration_type) \n" +
            "\tand (pr.price >= :price_minimum and pr.price <= :price_maximum)")
    List<Map<String, Object>> getKostByFilterSortAndAreaWithPagination(@Param("ac") Boolean ac,
                                                                       @Param("pillow") Boolean pillow,
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
                                                                       @Param("parking_car") Boolean parkingCar,
                                                                       @Param("parking_motorcycle") Boolean parkingMotorcycle,
                                                                       @Param("province") String province,
                                                                       @Param("city") String city, Pageable pageable);



    @Query(nativeQuery = true, value = "select distinct k.province, k.city, k.kost_id, k.kost_name \n" +
            "from kost k\n" +
            "where k.deleted_at is null \n" +
            "and k.enabled = true \n" +
            "and k.city ilike %:keyword%\n" +
            "or k.province ilike %:keyword%\n" +
            "or k.kost_name ilike %:keyword%")
    List<Map<String, Object>> getKostBySearchWithPagination(@Param("keyword") String keyword);


    @Query(value = "select \n" +
            "k.kost_id,\n" +
            "\tk.kost_name,\n" +
            "\tk.city,\n" +
            "\tk.address,\n" +
            "\tpr.price,\n" +
            "\tpr.duration_type,\n" +
            "\tk.kost_type_man,\n" +
            "\tk.kost_type_mixed,\n" +
            "\tk.kost_type_woman,\n" +
            "\tk.front_building_photo, \n" +
            "\tk.province,\n" +
            "\tk.description,\n" +
            "--fasilitas kost 12 \n" +
            "\tk.dispenser,\n" +
            "\tk.drying_ground,\n" +
            "\tk.electric,\n" +
            "\tk.kitchen,\n" +
            "\tk.kost_tv,\n" +
            "\tk.laundry,\n" +
            "\tk.living_room,\n" +
            "\tk.parking_car,\n" +
            "\tk.parking_motorcycle,\n" +
            "\tk.refrigerator,\n" +
            "\tk.water,\n" +
            "\tk.wifi, \n" +
            "\tr.room_name,\n" +
            "\tr.inside_room_photo,\n" +
            "--\tfacility\n" +
            "\tf.*,\n" +
            "\tr.available_room,\n" +
            "\tr.size_room,\n" +
            "--\trule\n" +
            "\tr2.identity_card,\n" +
            "\tr2.include_electricity,\n" +
            "\tr2.maxixmum_one,\n" +
            "\tr2.maximum_two,\n" +
            "\tr2.no_smoking,\n" +
            "\tr2.restricted_checkin,\n" +
            "\tr2.restricted_checkout,\n" +
            "\tr2.restricted_gender,\n" +
            "\tr2.restricted_guest,\n" +
            "\tr2.restricted_night,\n" +
            "\tr2.rule_name" +
            "\tfrom\n" +
            "\t kost k \n" +
            "left join room r on k.kost_id = r.kost_id and r.deleted_at is null\n" +
            "left join price pr on pr.room_id = r.room_id and pr.deleted_at is null \n" +
            "left join facility f on f.facility_id = r.facility_id and f.deleted_at is null\n" +
            "left join kost_rule kr on kr.kost_id = k.kost_id \n" +
            "left join \"rule\" r2 on r2.rule_id = kr.rule_id \n" +
            "where k.deleted_at is null\n" +
            "and pr.duration_type = 'MONTHLY'\n" +
            "and k.enabled = true\n" +
            "and r.enabled = true\n" +
            "and k.kost_id = :kost_id and pr.deleted_at is null\n" +
            "order by \n" +
            "pr.price asc", nativeQuery = true)
    List<Map<String, Object>> getKostById(@Param(value = "kost_id") Long id);



}