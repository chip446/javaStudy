package com.chip.rentcar;

import com.chip.rentcar.vehicle.IVehicle;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="phonebook_tbl")
public class CarEntity implements IVehicle {

}
