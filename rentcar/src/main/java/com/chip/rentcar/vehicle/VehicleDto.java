package com.chip.rentcar.vehicle;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto implements IVehicle {
    private Long id;
    private String company;
    private String model;
    private int year;
    private VehicleType type;
    private String color;
    private int km;
    private String factoryNumber;
    private String registNumber;
    private VehicleStatus status;

    @Override
    public String toString(){
        return String.format("ID:%6d, 제조사:%s, 모델:%s, 연식:%d, 타입:%s, 색상:%s, 총주행거리:%d, 공장번호:%s, 등록번호:%s, 상태:%s",
                this.id, this.company, this.model, this.year, this.type, this.color, this.km, this.factoryNumber, this.registNumber, this.status);
    }
}
