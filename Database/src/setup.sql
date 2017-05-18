create table WindowedMetrics (
	windowed_metric_id bigint unsigned not null primary key auto_increment,
	owner_id varchar(200) not null,
    sensor_type varchar(200) not null,
    window_size int unsigned not null,
    window_start bigint unsigned not null,
    sensor_avg double not null,
    sensor_min double not null, 
    sensor_max double not null,
    
    unique index (owner_id, sensor_type, window_size, window_start)
);