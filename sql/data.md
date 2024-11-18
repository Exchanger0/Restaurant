```sql
INSERT INTO address(country, city, street, number) VALUES
('Philippines', 'Lumbangan', 'Montana', 60194),
('China', 'Miaojie', 'Upham', 48292),
('Philippines', 'Simunul', 'Glendale', 416),
('Philippines', 'Buenavista', 'Cordelia', 3917),
('South Africa', 'Benoni', 'Elmside', 59126),
('Canada', 'Saint-Remi', 'Basil', 8),
('Greece', 'Makrychori', 'Amoth', 6545),
('Syria', 'Ihsim', 'Del ''Sol', 7536),
('Philippines', 'Masiga', 'Continental', 3957),
('South Korea', 'Hwado', '2''d', 377),
('Turkmenistan', 'Mary', 'Sullivan', 41),
('Indonesia', 'Mesa', 'Scofield', 99),
('Micronesia', 'Nukuoro', 'Sunbrook', 59426),
('Philippines', 'Cabangahan', 'Redwing', 563),
('China', 'Guanting', 'Manufacturers', 68500)
```


```python
import psycopg2
import psycopg2 as ps

images = []
paths = ['images/r1.png', 'images/r2.png', 'images/r3.png', 'images/r4.png', 'images/r5.png', 'images/r6.png', 
    'images/r7.png', 'images/r8.png', 'images/r9.png', 'images/r10.png', 'images/r11.png', 'images/r12.png', 
    'images/r13.png', 'images/r14.png', 'images/r15.png']

for p in paths:
    with open(p, "rb") as f:
        images.append(f.read())

print(images)
start_times = ['9:00', '8:00', '7:00', '6:00', '5:00', '10:00', '11:00', '9:00', '8:30', '12:00', '7:30', '10:00',
               '11:30', '12:00', '12:00']
end_times = ['21:00', '22:00', '23:00', '00:00', '22:30', '22:00', '20:00', '3:00', '1:00', '2:00', '00:30', '22:30',
             '23:30', '00:00', '00:30']

conn = ps.connect(database="restaurant", user="postgres", password="gt65hash87qq", host="127.0.0.1", port="5432")
conn.autocommit = True
cr = conn.cursor()

query = "INSERT INTO restaurant(image, address_id, start_time, end_time) VALUES (%s, %s, %s, %s)"
data = [(psycopg2.Binary(images[i]), i + 1, start_times[i], end_times[i]) for i in range(15)]

cr.executemany(query, data)

cr.close()
conn.close()
```