package test.scottishpower.smartmeter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMERACCOUNT")
public class CustomerAccount {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer accountId;

    @Column
    private Integer gasReadingMeterId;

    @Column
    private Integer electricityReadingMeterId;


}
