select Request.Request_id, Request.`Date`, Request.Comments, 
Offer.Offer_id, Customer.*, 
Carport.Carport_id, Carport.`Length` as Carport_length, Carport.`Width` as Carport_width, 
Roof.Roof_id, Roof.`Type` as Roof_type, Roof.Slope as Roof_slope, 
Shed.Shed_id, Shed.`Length` as Shed_length, Shed.`Width` as Shed_width, Shed.Cover as Shed_cover
from Request
left join Customer on Request.Customer_id = Customer.Customer_id
left join Carport on Request.Carport_id = Carport.Carport_id
left join Roof on Carport.Roof_id = Roof.Roof_id
left join Shed on Carport.Shed_id = Shed.Shed_id
left join Offer on Request.Request_id = Offer.Request_id
WHERE Request.Request_id = 1;