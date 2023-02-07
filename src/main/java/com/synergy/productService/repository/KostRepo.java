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

        // List<Kost> findByProfileId(Long profilepId);

        // @Query(value = "SELECT k FROM Kost k WHERE k.id = :id", nativeQuery = false)
        // Kost checkExistingKostId(Long id);

        @Query(value = "SELECT * FROM kost k WHERE k.kost_id = :kost_id AND k.enabled = true", nativeQuery = true)
        Kost checkExistingKostId(@Param("kost_id") Long id);

        @Query(value = "SELECT * FROM kost k WHERE k.kost_id = :kost_id", nativeQuery = true)
        Kost checkExistingKostIdAdmin(@Param("kost_id") Long id);

        @Query(value = "select * from kost k where k.enabled = :enabled", nativeQuery = true)
        public Page<Kost> getListDataAdmin(@Param("enabled") Boolean enabled, Pageable pageable);

        @Query(value = "SELECT * FROM kost k WHERE k.profile_id = :profileId", nativeQuery = true)
        public Page<Kost> getListDataTennant(@Param("profileId") Long profileId, Pageable pageable);

        @Query(nativeQuery = true, value = "select k.kost_id, k.kost_name, k.city, k.address, k.province, k.kost_type_man, k.kost_type_mixed, k.kost_type_woman, k.front_building_photo, r.room_id, price.price, price.duration_type\n" +
                                        "\tfrom\n" +
                                        "\tkost k\n" +
                                        "\tinner join room r on k.kost_id = r.kost_id\n" +
                                        "\tand r.deleted_at is null\n" +
                                        "\tand r.enabled = true\n" +
                                        "\tand r.room_id = (\n" +
                                        "\tselect ro.room_id from room ro, price pri where ro.room_id = pri.room_id and ro.kost_id = k.kost_id and pri.price = (\n" +
                                        "\tselect min(pric.price) from room rom, price pric where rom.room_id = pric.room_id and rom.kost_id = k.kost_id and pric.duration_type = :duration_type\n" +
                                        "\t) LIMIT 1\n" +
                                        "\t)\n" +
                                        "\tleft join (\n" +
                                        "\tselect room_id, min(price_id) as price_id from price where deleted_at is null and duration_type = :duration_type group by room_id\n" +
                                        "\t) pr on pr.room_id = r.room_id\n" +
                                        "\tleft join price on price.price_id = pr.price_id\n" +
                                        "\tleft join facility fa on fa.facility_id = r.facility_id\n" +
                                        "\tand fa.deleted_at is null\n" +
                                        "\twhere\n" +
                                        "\tk.deleted_at is null\n" +
                                        "\tand k.enabled is true\n" +
                                        "\tand (\n" +
                                        "\tlower(k.province) like (% :province %)\n" +
                                        "\tand lower(k.city) like (% :city %)\n" +
                                        "\tand (\n" +
                                        "\tpr.price between :price_minimum\n" +
                                        "\tand :price_maximum\n" +
                                        "\t)\n" +
                                        "\t)\n" +
                                        "\tand (\n" +
                                        "\t(fa.ac = :ac or :ac = '')\n" +
                                        "\tand (fa.pillow = :pillow or :pillow = '')\n" +
                                        "\tand (fa.fan = :fan  or :fan = '')\n" +
                                        "\tand (fa.furniture = :furniture or :furniture = '')\n" +
                                        "\tand (fa.shower = :shower or :shower = '')\n" +
                                        "\tand (fa.sitting_closet = :sitting_closet or :sitting_closet = '')\n" +
                                        "\tand (fa.springbed = :springbed or :springbed = '')\n" +
                                        "\tand (fa.table_learning = :table_learning or :table_learning = '')\n" +
                                        "\tand (fa.water_heater = :water_heater or :water_heater = '')\n" +
                                        "\tand (fa.inside_bathroom = :inside_bathroom or :inside_bathroom = '')\n" +
                                        "\tand (fa.non_sitting_closet = :non_sitting_closet or :non_sitting_closet = '')\n" +
                                        "\tand (fa.outside_bathroom = :outside_bathroom or :outside_bathroom = '' )\n" +
                                        "\tand (fa.windows = :windows or :windows = '')\n" +
                                        "\tand (fa.room_tv = :room_tv or :room_tv = '')\n" +
                                        "\tand (k.kost_tv = :kost_tv or :kost_tv = '')\n" +
                                        "\tand (k.electric = :electric or :electric = '')\n" +
                                        "\tand (k.laundry = :laundry or :laundry = '')\n" +
                                        "\tand (k.refrigerator = :refrigerator or :refrigerator = '')\n" +
                                        "\tand (k.water = :water or :water = '')\n" +
                                        "\tand (k.wifi = :wifi or :wifi = '')\n" +
                                        "\tand (k.dispenser = :dispenser or :dispenser = '')\n" +
                                        "\tand (k.drying_ground = :drying_ground or :drying_ground = '')\n" +
                                        "\tand (k.kitchen = :kitchen or :kitchen = '')\n" +
                                        "\tand (k.living_room = :living_room or :living_room = '')\n" +
                                        "\tand (k.parking_car = :parking_car or :parking_car = '')\n" +
                                        "\tand (k.parking_motorcycle = :parking_motorcycle or :parking_motorcycle = '')\n" +
                                        "\t)\n" +
                                        "\tand (\n" +
                                        "\t(k.kost_type_man = :kost_type_man or :kost_type_man = '')\n" +
                                        "\tand (k.kost_type_woman = :kost_type_woman or :kost_type_woman = '')\n" +
                                        "\tand (k.kost_type_mixed = :kost_type_mixed or :kost_type_mixed = '')\n" +
                                        "\t)")
        List<Map<String, Object>> getKostByFilterSortAndAreaWithPagination(@Param("ac") Boolean ac,
                        @Param("pillow") String pillow,
                        @Param("fan") String fan,
                        @Param("furniture") String furniture,
                        @Param("shower") String shower,
                        @Param("sitting_closet") String sittingCloset,
                        @Param("springbed") String springbed,
                        @Param("table_learning") String table,
                        @Param("water_heater") String waterHeater,
                        @Param("inside_bathroom") String insideBathroom,
                        @Param("non_sitting_closet") String nonSittingCloset,
                        @Param("outside_bathroom") String outsideBathroom,
                        @Param("windows") String windows,
                        @Param("room_tv") String roomTv,
                        @Param("kost_type_man") String kostTypeMan,
                        @Param("kost_type_woman") String kostTypeWoman,
                        @Param("kost_type_mixed") String kostTypeMixed,
                        @Param("duration_type") String durationType,
                        @Param("price_minimum") Double priceMinimum,
                        @Param("price_maximum") Double priceMaximum,
                        @Param("kost_tv") String kostTv,
                        @Param("electric") String electric,
                        @Param("laundry") String laundry,
                        @Param("refrigerator") String refrigerator,
                        @Param("water") String water,
                        @Param("wifi") String wifi,
                        @Param("dispenser") String dispenser,
                        @Param("drying_ground") String dryingGround,
                        @Param("kitchen") String kitchen,
                        @Param("living_room") String livingRoom,
                        @Param("parking_car") String parkingCar,
                        @Param("parking_motorcycle") String parkingMotorcycle,
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

        @Query(value = "select distinct \n" +
                        "\tk.kost_id,\n" +
                        "\tk.kost_name,\n" +
                        "\tk.city,\n" +
                        "\tk.address,\n" +
                        "\tk.kost_type_man,\n" +
                        "\tk.kost_type_mixed,\n" +
                        "\tk.kost_type_woman,\n" +
                        "\tk.front_building_photo, \n" +
                        "\tk.front_farbuilding_photo, \n" +
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
                        "\tpr.price,\n" +
                        "\tpr.duration_type,\n" +
                        "\tr.room_name,\n" +
                        "\tr.inside_room_photo,\n" +
                        "\tr.other_room_photo,\n" +
                        "\tr.available_room,\n" +
                        "\tr.room_id,\n" +
                        "\tr.size_room,\n" +
                        "--\tfacility\n" +
                        "\tf.*,\n" +
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
                        "\t room r \n" +
                        "left join kost k on k.kost_id = r.kost_id and r.deleted_at is null\n" +
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
                        "\tk.front_farbuilding_photo, \n" +
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
                        "\tr.other_room_photo,\n" +
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
                        "and k.kost_id = :kost_id and pr.deleted_at is null\n" +
                        "order by \n" +
                        "pr.price asc", nativeQuery = true)
        List<Map<String, Object>> getKostByIdAdmin(@Param(value = "kost_id") Long id);

}