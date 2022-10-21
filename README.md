# Smart Meter Details Read Sevice
Scottish Power test round 2

Service Name - SmartMeter
Service to find latest smart meter readings for customers.

Database design -
1. CustomerAccount - This table contains AccountNumber (int) with corresponding GasReadingMeterId (int),ElectricityReadingMeterId (int) and unique Id (int) as primary key.
                     One Account can have multiple meters or only electricity meter or only gas meter.
2. GasReading - This table contains Gas meter readings (int) with date (Date),  meter id (int) (references to GasReadingMeterId in CustomerAccount table) and unique primary key Id(int)

3. ElectricityReading - This table contains Electricity meter readings (int) with date (Date),  meter id (int) (references to ElectricityReadingMeterId in CustomerAccount table) and unique primary key Id(int)
                                                                 
                                                
